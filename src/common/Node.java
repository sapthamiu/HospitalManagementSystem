package src.common;
public class Node<T extends Object>
{
	public T data;
	public Node<T> next;
	public Node(T data){
		this.data = data;
		next = null;
	}
}