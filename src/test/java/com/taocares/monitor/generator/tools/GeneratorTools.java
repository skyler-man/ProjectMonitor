package com.taocares.monitor.generator.tools;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.Scanner;

/**
 * 代码生成所需要的tools
 *
 * @author lin
 * @date 2018年11月11日15:09:11
 */
public class GeneratorTools {

    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 字符串变驼峰
     */
    public static String stringToHump(String str) {
        String[] strs = str.split("_");
        String reStr = "";
        for (int i = 0; i < strs.length; i++) {
            reStr = reStr + upperCase(strs[i]);
        }
        return reStr;
    }

    /**
     * 首字母变大写
     */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }
}
