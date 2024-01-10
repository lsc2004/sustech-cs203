import java.io.*;
import java.util.StringTokenizer;

public class H23 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            LinkedList linkedList = new LinkedList();
            int condition1 = -1;
            for (int j = 0; j < n; j++) {
                int number = in.nextInt();
                if (condition1 > 0) {
                    if (isSmall(linkedList, number).equals("a")) {
                        node6 tempNode = linkedList.tail.prev;
                        node6 newNode = new node6(number, tempNode, linkedList.tail);
                        linkedList.tail.prev = newNode;
                        tempNode.next = newNode;
                        linkedList.currentNode = newNode;
                        remove(linkedList.currentNode.prev);
                        condition1 = -1;
                    } else {
                        compile(n, linkedList, j, number);
                    }
                } else {
                    if (isSmall(linkedList, number).equals("a")) {
                        node6 tempNode = linkedList.tail.prev;
                        node6 newNode = new node6(number, tempNode, linkedList.tail);
                        linkedList.tail.prev = newNode;
                        tempNode.next = newNode;
                        linkedList.currentNode = newNode;
                    } else {
                        if (indexList.getLength() == 0 || linkedList.currentNode.prev != indexList.nodes[indexList.getLength() - 1]) {
                            indexList.insert(indexList.getLength(), linkedList.currentNode.prev);
                        }
                        compile(n, linkedList, j, number);
                        condition1 = 1;
                    }
                }
            }
            while (indexList.getLength() > 0) {
                int position = 0;
                int length1 = indexList.getLength();
                int counter = 0;
                while (counter < length1) {
                    if (compare(indexList.nodes[counter], indexList.nodes[counter].next) == 1 && indexList.nodes[counter].next.value != 100003) {
                        if (indexList.nodes[counter].next != indexList.nodes[counter + 1]) {
                            int condition2 = -1;
                            node6 Node = new node6(-2, null, null);
                            if (indexList.isNothead(counter)) {
                                Node = indexList.nodes[counter].prev;
                                condition2 = 1;
                            }
                            remove(indexList.nodes[counter].next);
                            remove(indexList.nodes[counter]);
                            indexList.delete(counter);
                            position = getPosition(position, condition2, Node);
                        } else {
                            int condition3 = -1;
                            node6 Node = new node6(-2, null, null);
                            if (indexList.isNothead(counter)) {
                                Node = indexList.nodes[counter].prev;
                                condition3 = 1;
                            }
                            int Int = getInt(counter);
                            int k = getK(counter, Int);
                            if (k == Int) {
                                if (compare(indexList.nodes[Int + counter], indexList.nodes[counter + Int].next) == 1) {
                                    remove(indexList.nodes[counter + Int].next);
                                }
                                remove(indexList.nodes[counter + Int]);
                                indexList.delete(counter + Int);
                            }
                            counter = counter + k;
                            position = getPosition(position, condition3, Node);
                        }
                    } else {
                        indexList.delete(counter);
                    }
                    counter += 1;
                }
            }
            linkedList.currentNode = linkedList.head;
            while (linkedList.currentNode.next.value != 100003) {
                out.print(linkedList.currentNode.next.value + " ");
                linkedList.currentNode = linkedList.currentNode.next;
            }
            out.println(" ");
        }
        out.close();
    }
    public static int getPosition(int position, int condition, node6 tempNode) {
        if (condition > 0 && isPosition(position, tempNode) < 0) {
            indexList.insert(position, tempNode);
            position += 1;
        }
        return position;
    }
    public static int compare(node6 a1, node6 a2) {
        return Integer.compare(a1.value, a2.value);
    }
    public static int getK(int counter, int anInt) {
        int k = 0;
        while (k < anInt) {
            if (compare(indexList.nodes[counter + k], indexList.nodes[1 + k + counter]) == -1 || compare(indexList.nodes[counter + k], indexList.nodes[1 + k + counter]) == 0) {
                remove(indexList.nodes[k + counter]);
                indexList.delete(counter + k);
                break;
            }
            remove(indexList.nodes[k + counter]);
            indexList.delete(counter + k);
            k += 1;
        }
        return k;
    }
    public static int getInt(int counter) {
        int Int = 0;
        while (indexList.nodes[counter + Int].next == indexList.nodes[counter + 1 + Int]) {
            Int += 1;
        }
        return Int;
    }
    public static void compile(int n, LinkedList linkedList, int j, int number) {
        if (j + 1 == n) {
            remove(linkedList.currentNode);
        } else {
            node6 tempNode = linkedList.tail.prev;
            node6 newNode = new node6(number, tempNode, linkedList.tail);
            linkedList.tail.prev = newNode;
            tempNode.next = newNode;
            linkedList.currentNode = newNode;
            remove(linkedList.currentNode.prev);
        }
    }
    public static void remove(node6 linkedList) {
        node6 tempNode1 = linkedList.next;
        node6 tempNode2 = linkedList.prev;
        tempNode1.prev = tempNode2;
        tempNode2.next = tempNode1;
    }
    public static int isPosition(int position, node6 tempNode) {
        if (position != 0 && indexList.nodes[position - 1] == tempNode) {
            return 1;
        }
        return -1;
    }
    public static String isSmall(LinkedList linkedList, int number) {
        if (number < linkedList.currentNode.value) {
            return "b";
        }
        return "a";
    }
}
class LinkedList {
    node6 head;
    node6 tail;
    node6 currentNode;
    public LinkedList() {
        node6 head = new node6(-1, null, null);
        this.head = head;
        node6 tail = new node6(100003, head, null);
        this.tail = tail;
        this.head.next = tail;
        this.currentNode = this.head;
    }
}
class node6 {
    node6 next;
    node6 prev;
    int value;
    public node6(int value, node6 prev, node6 next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}
class indexList {
    static node6[] nodes;
    static int length = 0;
    public indexList() {
        nodes = new node6[10003];
    }
    public static boolean isNothead(int counter) {
        return nodes[counter].prev.value != -1;
    }
    public static void delete(int index) {
        nodes[index] = null;
        length--;
    }
    public static void insert(int index, node6 node) {
        nodes[index] = node;
        length++;
    }
    public static int getLength() {
        return length;
    }
}

class QReader {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}



