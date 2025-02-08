package priv.wz.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次
 * 数字 1-9 在每一列只能出现一次
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char current = board[i][j];
                if (current != '.') {
                    if (!set.add(current + " in row " + i) ||  // 判断当前行
                            !set.add(current + " in col " + j) ||  // 判断当前列
                            !set.add(current + " in box " + i / 3 + ", " + j / 3)) // 判断当前3*3宫
                        return false;
                }
            }
        }
        return true;
    }
}
