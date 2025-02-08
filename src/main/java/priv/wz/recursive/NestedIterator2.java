package priv.wz.recursive;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 参考1，可以将嵌套列表看成一个树
 */
public class NestedIterator2 implements Iterator<Integer> {

    private List<Integer> values;
    private Iterator<Integer> it;

    public NestedIterator2(List<NestedIterator.NestedInteger> list) {
        values = new ArrayList<>();
        dfs(list);
        it = values.iterator();
    }

    private void dfs(List<NestedIterator.NestedInteger> list) {
        for (NestedIterator.NestedInteger cur : list) {
            if (cur.isInteger()) {
                values.add(cur.getInteger());
            } else {
                dfs(cur.getList());
            }
        }
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public Integer next() {
        return it.next();
    }
}
