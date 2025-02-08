package priv.wz.recursive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；该列表的元素也可能是整数或者是其他列表。
 * 请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。实现扁平迭代器类 NestedIterator:
 * <p>
 * NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
 * int next() 返回嵌套列表的下一个整数。
 * boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：nestedList = [[1,1],2,[1,1]]
 * 输出：[1,1,2,1,1]
 * 解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 * <p>
 * 示例 2：
 * 输入：nestedList = [1,[4,[6]]]
 * 输出：[1,4,6]
 * 解释：通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 */
public class NestedIterator implements Iterator<Integer> {

    private List<NestedInteger> nestedList;
    private NestedIterator curList;
    private int index;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        if (nestedList == null) {
            throw new IllegalArgumentException();
        }
        index = -1;
    }

    @Override
    public Integer next() {
        if (curList != null && curList.hasNext()) {
            return curList.next();
        } else {
            index++;
            while (index < nestedList.size()) {
                NestedInteger cur = nestedList.get(index);
                if (cur.isInteger()) {
                    curList = null;
                    return cur.getInteger();
                } else {
                    // list
                    curList = new NestedIterator(cur.getList());
                    if (curList.hasNext()) {
                        return curList.next();
                    }
                }
                index++;
            }
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean hasNext() {
        if (curList != null && curList.hasNext()) {
            return true;
        }
        // 处理当前层
        int cur = index;
        cur++;
        while (cur < nestedList.size()) {
            NestedInteger next = nestedList.get(cur);
            if (next.isInteger()) {
                return true;
            } else {
                // list
                List<NestedInteger> curList = next.getList();
                if (!curList.isEmpty() && new NestedIterator(curList).hasNext()) {
                    return true;
                }
            }
            cur++;
        }
        return false;
    }


    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public static class Impl implements NestedInteger {

        private Integer value;
        private List<NestedInteger> list;

        public Impl(Integer value) {
            this.value = value;
        }

        public Impl(List<NestedInteger> list) {
            this.list = list;
        }

        @Override
        public boolean isInteger() {
            return value != null;
        }

        @Override
        public Integer getInteger() {
            return value;
        }

        @Override
        public List<NestedInteger> getList() {
            return list;
        }
    }

    public static void main(String[] args) {

        NestedInteger v1 = new Impl(1);
        NestedInteger v2 = new Impl(1);
        List<NestedInteger> l1 = new ArrayList<>(2);
        l1.add(v1);
        l1.add(v2);
        NestedInteger L1 = new Impl(l1);
        NestedInteger v3 = new Impl(2);
        NestedInteger v4 = new Impl(1);
        NestedInteger v5 = new Impl(1);
        List<NestedInteger> l2 = new ArrayList<>(2);
        l2.add(v4);
        l2.add(v5);
        NestedInteger L2 = new Impl(l2);
        List<NestedInteger> LL = new ArrayList<>(3);
        LL.add(L1);
        LL.add(v3);
        LL.add(L2);

        NestedIterator iter = new NestedIterator(LL);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
