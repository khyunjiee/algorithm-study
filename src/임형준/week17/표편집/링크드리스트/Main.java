package src.알고리즘스터디.날짜12월3째주.표편집.링크드리스트;

public class Main {

    static class Node{
        int data;
        Node next = null;
        Node(int data){
            this.data = data;
        }
        Node(){}
    }

    static class LinkedList{
        Node header;

        LinkedList(){
            header = new Node();
        }

        void append(int data){
            Node end = new Node(data);
            Node n = header;
            while(n.next!=null) {
                n = n.next;
            }
            n.next=end;
        }

        void print(){
            Node n = header.next;
            while(n.next!=null){
                System.out.println(n.data);
                n=n.next;
            }
            System.out.println(n.data);
        }

        void delete(int data){
            Node n = header;
            while(n.next!=null){
                if(n.next.data == data){
                    n.next = n.next.next;
                }else{
                    n=n.next;
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.append(1);
        ll.append(2);
        ll.append(3);
        ll.append(4);
        ll.append(5);

        ll.print();

        ll.delete(3);

        ll.print();
    }
}
