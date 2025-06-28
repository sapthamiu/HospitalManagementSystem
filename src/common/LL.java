package src.common;
import src.common.Node;
public class LL<T extends Object>
{
	public Node<T> l;
	public LL(){
		l = null;
	}
	public void insert(T x){
		Node<T> n = new Node<T>(x);
		if(l == null){
			l = n;
			return;
		}
		n.next = l;
		l = n;
	}
	public T delete(T x){
		T y;
		boolean flag = false;
		if(l == null){
			System.out.println("List is empty");
			return (T)(new Object());
		}
		if(l.data.equals(x)){
			Node<T> temp = l;
			l = l.next;
			y = temp.data;
			temp = null;
			return y;
		}
		Node<T> t = l;
		while(t.next != null){
			if(x.equals(t.next.data)){
				flag = true;
				break;
			}
			t = t.next;
		}
		if(flag == false){
			System.out.println("Not found");
			return (T)(new Object());
		}
		Node<T> temp = t.next;
		t.next = temp.next;
		y = temp.data;
		temp = null;
		return y;
	}
	public void display()
	{
		if(l == null){
			System.out.println("List is empty");
			return;
		}
		Node<T> n = l;
		while(n != null){
			System.out.println(n.data);
			n = n.next;
		}
	}
}