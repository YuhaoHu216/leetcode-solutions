package question35;

import java.util.HashMap;
import java.util.Map;

/**
 * 146.LRU缓存
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * 示例：
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 */
class LRUCache {
    // 底层是一个双向链表,最新操作过的数就排在链表最前面.
    // 用一个hashmap来维护节点值和节点,两个哨兵节点来维护头尾

    class Node {
        int key, value; // key是数据在缓存中的唯一标识,value是对对应的数据
        Node pre, next;
        Node(int k,int v){key = k;value = v;}
    }

    Map<Integer,Node> map;  // 用于取键的map
    Node head,tail;         // 伪节点用于界定头尾
    int capacity;           // 初始化用的容量

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer,Node>();
        head = new Node(0,0);
        tail = new Node(0,0);
        // 节点初始化要有指向
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        // 将该节点置于伪头节点后
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        // 分两种情况,缓存里已有和没有
        if(map.containsKey(key)){
            // 如果缓存里已有数,就更新
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        }else{
            // 如果缓存里没有就新增(判断是否到容量)
            Node node = new Node(key,value);
            addToHead(node);    // 注意因为是新节点,所以用addToHead
            map.put(key,node);
            if(map.size() > capacity){
                // 移出最末尾的数从链表到map
                Node removeNode = removeTail();
                map.remove(removeNode.key);
            }
        }
    }

    // 要用到的辅助函数
    void moveToHead(Node node){
        // 节点首先从原地方断开
        node.pre.next = node.next;
        node.next.pre = node.pre;
        // 移动到伪头节点后
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    void addToHead(Node node){
        // 移动到伪头节点后
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    Node removeTail(){
        Node node = tail.pre;
        // 节点首先从原地方断开
        node.pre.next = node.next;
        node.next.pre = node.pre;

        return node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
