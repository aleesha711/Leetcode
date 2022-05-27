fun main(args: Array<String>) {
    println("Welcome")
    val array = intArrayOf(1,2,3,4,5,6,6,6,5)
    println(containsDuplicate1(array).toString())
    println(containsDuplicate2(array).toString())
    println("isAnagram1 " + isAnagram1("cat","tac").toString())
    println("isAnagram2 " + isAnagram2("anagram","nagaram").toString())
    val stringArray =  twoSum2(array,4).map { it.toString() }.toTypedArray()
    println("TwoSum1" + stringArray.contentToString())
    intersection2(array, array)
    val strs = arrayOf("eat","tea","tan","ate","nat","bat")
    groupAnagrams1(strs)
    groupAnagrams2(strs)
    val ar = intArrayOf(1,1,1,2,2,3)
    topKFrequent(ar,2)
    val nums = intArrayOf(1,2,3,4)
    productExceptSelf(nums)
    val list = listOf("leet","code","love","you")
    println("encoded " +  encode(list))
    println("decoded " +  decode(encode(list)).toString())

    println("encoded String " +  encodeString(list))
    println("decoded String " +  decodeString(encodeString(list)).toString())
    val longestConsecutive = intArrayOf(100,4,200,1,3,2)
    println("longest consecutive " +  longestConsecutive(longestConsecutive).toString())

    val threeSum = intArrayOf(-1,0,1,2,-1,-4)
    println("threeSum" +  threeSum(threeSum).toString())
    val twoSum = intArrayOf(4,1,5,0,1,2,3,1,4)
    println("twoSum" +  twoSumList(twoSum,5).toString())
    println("Trap Raining water " +  trap(twoSum).toString())

}

//1. Easy -> Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
// SOLUTION 1
fun containsDuplicate1(nums: IntArray): Boolean {
    val numbers = HashSet<Int>()
    for(num in nums) {
        if(numbers.contains(num)) return true
        numbers.add(num)
    }

    return false
}

// SOLUTION 2
fun containsDuplicate2(nums: IntArray): Boolean {
    val set = nums.toHashSet()
    if(nums.size == set.size) return false
    return true
}

//<------------------------------------------------------------------------------------------------------->

//2. Easy -> Given two strings s and t, return true if t is an anagram of s, and false otherwise.
// SOLUTION 1
fun isAnagram1(s: String, t: String): Boolean {

    if(s.length != t.length) return false

    val sCharArray = s.toCharArray()
    val tCharArray = t.toCharArray()

    tCharArray.sort()
    sCharArray.sort()

    return sCharArray.contentEquals(tCharArray)
}

// SOLUTION 2
fun isAnagram2(s: String, t: String): Boolean {

    val count = IntArray(26)

    if(s.length != t.length) return false

    val sCharArray = s.toCharArray()
    val tCharArray = t.toCharArray()

    for(i in sCharArray.indices) {
        count[sCharArray[i] - 'a']++
        count[tCharArray[i] - 'a']--
    }

    count.forEach {
        if(it !=0) return false
    }

    return true
}

//<------------------------------------------------------------------------------------------------------->

//3. Easy -> Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//Solution 1
fun twoSum1(nums: IntArray, target: Int): IntArray {

    var complimentHashmap = HashMap<Int, Int>()

    for(i in nums.indices) {
        var compliment =  target - nums.get(i)
        if(complimentHashmap.contains(compliment))
            return intArrayOf(complimentHashmap.get(compliment)!!, i)

        complimentHashmap.put(nums.get(i), i)
    }

    return intArrayOf(0)

}

//Solution 2
fun twoSum2(nums: IntArray, target: Int): IntArray {
    if(nums.isEmpty())
        return intArrayOf(0)

    for(i in nums.indices)
        for(j in i+1 until nums.size) {
            if(nums[i] + nums[j] == target) {
                return intArrayOf(i,j)
            }
        }

    return intArrayOf(0)
}

//<------------------------------------------------------------------------------------------------------->
//4. Easy --> return an array of intersection
fun intersection1(nums1: IntArray, nums2: IntArray): IntArray {
    var result = intArrayOf()
    for(i in nums1.indices) {
        for(j in nums2.indices) {
            if(nums1[i] == nums2[j])
                result[nums1[i]]
        }
    }

    return result
}

fun intersection2(nums1: IntArray, nums2: IntArray): IntArray {

    val set1= nums1.toSet()
    val set2= nums2.toSet()
    val result = set1.intersect(set2)
    return result.toIntArray()
}
//<------------------------------------------------------------------------------------------------------->
//5. Medium --> Given an array of strings strs, group the anagrams together. You can return the answer in any order.

fun groupAnagrams1(strs: Array<String>): List<List<String>> {

    var hashmap = hashMapOf<String, List<String>>()

    for(i in strs.indices) {

        val key = countAscii(strs.get(i))
        if(hashmap.containsKey(key))
            {
                var ar = hashmap.get(key)!!.toMutableList()
                ar.add(strs[i])

                hashmap.put(key, ar)
            }

        else
        {
            hashmap.put(key, listOf(strs[i]))
        }

    }

    return hashmap.values.toList()
}

fun countAscii(name: String): String {
    val count = IntArray(26)

    val nameArray = name.toCharArray()

    for(i in nameArray.indices) {
        count[nameArray[i] - 'a']++
    }

    count.contentToString()
    return count.contentToString()

}
//Solution 2
fun groupAnagrams2(strs: Array<String>): List<List<String>> {
    return strs.groupBy { it.toCharArray().sorted().toString() }.values.toList()
}

//6. Medium -> Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
//Solution 1
fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val sortedList = mutableListOf<Int>()
    val kList = mutableListOf<Int>()
    val hashMap = HashMap<Int,Int>() // value, count

    if(nums.isEmpty())
        return intArrayOf()

    for(i in nums.indices) {
        if (hashMap.containsKey(nums[i])) {
            hashMap[nums[i]] = hashMap.getValue(nums[i]) + 1
        }
        else {
            hashMap[nums[i]] = 1
        }
    }
    //sort list with count values
    hashMap.entries.sortedByDescending { it.value }.forEach {
            sortedList.add(it.key)
    }

    //and then return elements based on K (list is already sorted with count/frequency)
    for(i in sortedList.indices) {
        if(i < k) {
            kList.add(sortedList[i])
        }
    }

    return kList.toIntArray()
}

//<------------------------------------------------------------------------------------------------------->
//6. Medium --> Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

fun productExceptSelf(nums: IntArray): IntArray {

    val leftProductArray = IntArray(nums.size)
    val rightProductArray = IntArray(nums.size)
    val resultArray = IntArray(nums.size)
    leftProductArray[0] = 1
    rightProductArray[nums.size -1] = 1


    for(i in 1 until nums.size) {
        leftProductArray[i] = leftProductArray[i-1] * nums[i-1]
    }

    for(i in nums.size-2 downTo 0) {
        rightProductArray[i] = rightProductArray[i+1] * nums[i+1]
    }

    for(i in nums.indices) {
        resultArray[i] = rightProductArray[i] * leftProductArray[i]
    }

    return resultArray
}

//<------------------------------------------------------------------------------------------------------->
//6. Medium --> Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
//Please implement encode and decode
fun encode(strs: List<String>): String {
    val encodedString = StringBuilder()
    for (str in strs) {
        val length = str.length
        encodedString.append("$length#")
        encodedString.append(str)
    }
    return encodedString.toString()
}

fun decode(str: String): List<String> {
    val decodedStrings = mutableListOf<String>()
    var i = 0
    while (i < str.length) {
        var length = ""
        while (str[i] != '#') {
            length += str[i]
            i++
        }
        val wordLength = length.toInt()
        i++
        var word = ""
        for (j in i until wordLength + i) {
            word += str[j]
        }
        decodedStrings.add(word)
        i += wordLength
    }
    return decodedStrings
}

fun encodeString(list: List<String>) : String {
    val encodedString = StringBuilder()
    for(str in list) {
        val length = str.length
        encodedString.append("$length%")
        encodedString.append(str)
    }

    return encodedString.toString()
}

fun decodeString(str: String) : List<String> {
    val list = mutableListOf<String>()
    var i = 0
    while (i < str.length) {

        var length = ""
        if(str[i] != '%') {
            length += str[i].toString()
            i++
        }

        var wordLength = length.toInt()
        i++

        var word = ""

        for(j in i until wordLength + i) {
            word += str[j].toString()
        }

        list.add(word)
        i+= wordLength
    }

    return list
}

//<------------------------------------------------------------------------------------------------------->
//6. Medium --> Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence. You must write an algorithm that runs in O(n) time.

fun longestConsecutive(nums: IntArray): Int {
    var max = 0
    val hashmap: HashMap<Int, Boolean> = hashMapOf()

    for(i in 0 .. nums.lastIndex) {
        hashmap[nums[i]] = true
    }

    for(i in 0 .. nums.lastIndex) {
        if(hashmap.contains(nums.get(i) -1)) {
            hashmap.put(nums.get(i), false)
        }
    }


    for(key in hashmap.keys) {
        if(hashmap.get(key) == true) {
            max = Math.max(max, getCountOfConsecutive(hashmap, key ))
        }
    }

    return max
}

private fun getCountOfConsecutive(hashmap: HashMap<Int, Boolean>, key: Int): Int {
    var count = 0
    var num = key
    while(hashmap.contains(num)) {
        count++
        num++
    }

    return count
}


fun threeSumX(nums: IntArray): List<List<Int>> {



    val list = mutableListOf<Int>()
    val fList = mutableListOf<List<Int>>()

    fList.toList()
    if(nums.isEmpty())
        return emptyList()

    for(i in nums.indices) {
        for (j in i+1 until nums.size){
            for(k in j+1 until nums.size-1){
                if(nums.get(i) + nums.get(j) + nums.get(k) == 0) {
                    list.add(nums.get(i))
                    list.add(nums.get(j))
                    list.add(nums.get(k))
                    fList.add(list)
                }
            }
        }
    }

    return fList

}

// 3 sum -- Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
//Notice that the solution set must not contain duplicate triplets.
fun threeSum(nums: IntArray): List<List<Int>> {

    //array must be sorted

    val target = 0
    var fList = mutableListOf<List<Int>>()

    if(nums.isEmpty() && nums.size < 3)
        return emptyList()

    nums.sort()

    for(i in 0 ..nums.size-2) {

        if (i!=0 && nums.get(i) == nums.get(i-1)) {
            continue
        }

        //set variable to 1st position - fix for 1st iteration
        var a1 = nums.get(i)
        var newTarget = target - a1

        //check remaining space after fixing 1st position
        val subList = twoSum(nums, i+1, nums.size-1, newTarget)

        for(list in subList) {
            list.add(a1)
            fList.add(list)

        }
    }


    return fList

}

fun twoSum(nums: IntArray, p1: Int, p2: Int, target: Int): List<MutableList<Int>> {

    var fList = mutableListOf<MutableList<Int>>()
    var left = p1
    var right = p2
    while(left < right) {
        //to avoid duplicate
       // check left pointer is not on 1st position of space
        if(left!=p1 && nums.get(left) == nums.get(left - 1)) {
            left ++
            continue
        }

        var sum = nums.get(left) + nums.get(right)
        if(sum == target) {
            //its mean pairs found
            var sList = mutableListOf<Int>()

            sList.add(nums[left])
            sList.add(nums[right])

            // move both pointers now
            left++
            right--

            fList.add(sList)


        } else if(sum > target) {
            //its mean we need to add small value so move right pointer because array is is asc order
            right--
        } else {
            left++
        }

    }

    return fList
}


fun twoSum(nums: IntArray, target: Int): IntArray {

    //array must be sorted for this algorithm

    var array = IntArray(2)
    var left = 0
    var right = nums.size - 1

    if(nums.isEmpty() && nums.size < 2) {
        return intArrayOf()
    }

    while(left < right) {

        //to avoid duplicate
        if(left!=0 && nums.get(left) == nums.get(left - 1)) {
            left ++
            continue
        }

        var sum = nums.get(left) + nums.get(right)
        if(sum == target) {
            //its mean pairs found

            array[0] = left+1
            array[1] = right +1

            // move both pointers now
            left++
            right--

        } else if(sum > target) {
            //its mean we need to add small value so move right pointer because array is is asc order
            right--
        } else {
            left++
        }

    }

    return array
}

// 2 sum list -- In an array find pairs who can make upto the target avoid returning duplicated pairs
fun twoSumList(nums: IntArray, target: Int): List<List<Int>> {

    //array must be sorted for this algorithm
    var fList = mutableListOf<List<Int>>()
    var left = 0
    var right = nums.size - 1


    if(nums.isEmpty() && nums.size < 2)
        return emptyList()

    nums.sort()

    while(left < right) {

        //to avoid duplicate
        if(left!=0 && nums.get(left) == nums.get(left - 1)) {
            left ++
            continue
        }

        var sum = nums.get(left) + nums.get(right)

        if(sum == target) {
            //its mean pairs found
            var sList = mutableListOf<Int>()

            sList.add(nums[left])
            sList.add(nums[right])

            // move both pointers now
            left++
            right--

            fList.add(sList)


        } else if(sum > target) {
            //its mean we need to add small value so move right pointer because array is is asc order
            right--
        } else {
            left++
        }

    }

    return fList
}

// Trapping Rain Water - Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
fun trap(height: IntArray): Int {

    //step1 .. maintain left array of maximum height .. by default left one is index 0
    //step2 .. maintain right array of maximum height ..by default right one is index n-1
    //step3 .. find minimum height betwwen left and right array of i and then minus height of i ..                     // 0 means no water trapped three


    val leftArray = IntArray(height.size)
    val rightArray = IntArray(height.size)
    leftArray[0] = height[0]
    rightArray[height.size-1] = height[height.size - 1]
    var sum = 0

    for(i in 1..height.size-1) {
        leftArray[i] = Math.max(leftArray[i-1], height[i])
    }

    for(i in height.size-2 downTo 0) {
        rightArray[i] = Math.max(rightArray[i+1], height[i])
    }


    for(i in height.indices) {
        sum += (Math.min(leftArray[i], rightArray[i])) - height[i]

    }

    return sum

}

