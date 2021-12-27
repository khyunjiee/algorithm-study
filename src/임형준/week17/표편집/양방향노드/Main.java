package src.알고리즘스터디.날짜12월3째주.표편집.양방향노드;

public class Main {

    static class Node{
        int val;
        Node next;
        Node prev;

        Node(int val){
            this.val=val;
            this.next=null;
            this.prev=null;
        }
    }

    static class DoubleLinkedList{
        Node head;
        Node tail;
        int size=0;

        void addFirst(int val){
            Node newNode = new Node(val);
            newNode.next = head;

            if(head!=null) head.prev = newNode;

            head = newNode;
            size++;
            if(head.next==null) tail=head;
        }

        void addLast(int val){
            if(size==0){
                addFirst(val);
            }else{
                Node newNode = new Node(val);
                tail.next = newNode;
                newNode.prev = tail;
                tail=newNode;
                size++;
            }
        }

        void add(int k,int val){
            if(k==0){
                addFirst(val);
            }else{

            }
        }
    }

    public static void main(String[] args) {

    }
}
