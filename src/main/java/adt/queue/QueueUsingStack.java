package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;
	private int elements;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
		elements = -1;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) 
			throw new QueueOverflowException();
		
		if (element != null) {
			try {
				stack1.push(element);
			} catch (StackOverflowException e) {
				throw new QueueOverflowException();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty())
			throw new QueueUnderflowException();
		
		T elem = null;
		
		if (stack2.isEmpty())
			try {
				stack2.push(stack1.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		
		return stack2.pop();
	}

	@Override
	public T head() {
		T elem = null;
		
		if (!stack2.isEmpty()) {
			elem = stack2.top();
		}
		
		return elem;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}
	
	private void copyElements(Stack<T> st1, Stack<T> st2) {
		while (!st1.isEmpty())
			try {
				st2.push(st1.pop());
			} catch (StackOverflowException | StackUnderflowException e) {
				break;
			}
	}

}