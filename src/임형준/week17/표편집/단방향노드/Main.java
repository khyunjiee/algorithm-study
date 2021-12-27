package src.알고리즘스터디.날짜12월3째주.표편집.단방향노드;

public class Main {

    static class Node{
        int val;
        Node next;

        Node(int val){
            this.val=val;
        }

        void append(int val){
            Node end = new Node(val);
            Node n = this;
            while(n.next!=null){
                n=n.next;
            }
            n.next = end;
        }

        void print(){
            Node n = this;
            while(n.next!=null){
                System.out.println(n.val);
                n=n.next;
            }
            System.out.println(n.val);
        }
    }

    public static void main(String[] args) {
        Node n = new Node(4);
        n.append(3);
        n.append(5);
        n.append(1);
        n.append(2);
        n.append(3);

        n.print();
    }
}
