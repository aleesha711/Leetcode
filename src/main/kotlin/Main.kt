import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    println("Welcome")
    val array = intArrayOf(1,2,3,4,5,6)
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
//4. Medium --> Given an array of strings strs, group the anagrams together. You can return the answer in any order.

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
