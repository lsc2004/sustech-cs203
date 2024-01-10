import java.io.*;
import java.util.StringTokenizer;

public class H24 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            Stack1 stack1 = new Stack1();
            char[] m = in.next().toCharArray();
            for (int j = 0; j < n; j++) {
                stack1.push(m[j]);
                if (j > 0) {
                    if (stack1.s[stack1.top] == ')' && stack1.s[stack1.top - 1] == '(') {
                        stack1.pop();
                        stack1.pop();
                    } else if (stack1.s[stack1.top] == ']' && stack1.s[stack1.top - 1] == '[') {
                        stack1.pop();
                        stack1.pop();
                    } else if (stack1.s[stack1.top] == '}' && stack1.s[stack1.top - 1] == '{') {
                        stack1.pop();
                        stack1.pop();
                    }
                }
            }
            if (stack1.top == -1) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
        out.close();
    }
}

class Stack1 {
    int maxTop = 30000;
    char[] s;
    int top = -1;

    public Stack1() {
        this.s = new char[maxTop];
    }

    public void push(char a) {
        top++;
        s[top] = a;
    }

    public void pop() {
        top--;
    }
}