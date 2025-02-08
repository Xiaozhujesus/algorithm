package priv.wz.bit;

public class Op {
    // 获取 n 位置 pos 的值
    int get(int pos, int n) {
        return n & (1 << pos);
    }

    // 将 n 位置 pos 的值设置为 value（0 或 1）
    void set(int pos, int n, int val) {
        // 参数校验
        if (pos < 0 || pos > 31) {
            throw new IllegalArgumentException("位位置必须在 0-31 之间");
        }
        if (val != 0 && val != 1) {
            throw new IllegalArgumentException("值必须是 0 或 1");
        }

        if (val == 1) {
            // 将指定位设置为1：使用按位或操作
            n = n | (1 << pos);
        } else {
            // 将指定位设置为0：先取反掩码，然后与操作
            n = n & ~(1 << pos);
        }
    }
}
