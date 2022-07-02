package com.bridgelabz;

import java.util.Random;

class Queue<E> {
	private Node<E> start = null;
	private Node<E> end = null;
	private int size = 0;

	private class Node<V> {
		private V element;
		private Node<V> next;
	}

	public Queue() {
	}

	public void enqueue(E element) {
		if (end == null) {
			Node<E> temp = new Node<E>();
			temp.element = element;
			start = end = temp;
			size++;
			return;
		}
		end.next = new Node<E>();
		end = end.next;
		end.element = element;
		size++;
	}

	public E dequeue() {
		if (start == null) {
			return null;
		}
		Node<E> temp = start;
		start = start.next;
		size--;
		return temp.element;

	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public class DeckOfCards {

		public static String[][] cardDistribute() {
			String arr[][] = new String[4][13];
			cardInsert(arr);
			cardShuffle(arr);

			String playercard[][] = new String[4][9];
			for (int i = 0; i < playercard.length; i++) {
				for (int j = 0; j < playercard[i].length; j++) {
					playercard[i][j] = arr[i][j];
				}
			}
			return playercard;
		}

		public static void cardInsert(String arr[][]) {
			String Suits[] = { "Clubs", "Diamonds", "Hearts", "Spades" };
			String Rank[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "King", "Queen", "Ace" };

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					arr[i][j] = Suits[i] + " " + Rank[j];
				}
			}
		}

		public static void cardShuffle(String arr[][]) {
			Random r1 = new Random();
			for (int i = 0; i < 150; i++) {
				int x1 = r1.nextInt(4);
				int x2 = r1.nextInt(13);
				int x3 = r1.nextInt(4);
				int x4 = r1.nextInt(13);
				swap(arr, x1, x2, x3, x4);
			}
		}

		public static void swap(String arr[][], int x1, int x2, int x3, int x4) {
			String temp = arr[x1][x2];
			arr[x1][x2] = arr[x3][x4];
			arr[x3][x4] = temp;
		}

		public static Queue<Queue<String>> cardSort(String playercard[][]) {
			Queue<Queue<String>> sortedcard = new Queue<>();

			String rank[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
			int arr[] = new int[9];
			int index = 0;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 9; j++) {
					String temp[] = (playercard[i][j] + " ").split(" ");
					for (int k = 0; k < 13; k++) {
						if (temp[1].equals(rank[k])) {
							arr[index] = k;
							index++;
						}
					}
				}
				System.out.println();
				index = 0;
				for (int k1 = 0; k1 < arr.length - 1; k1++) {
					for (int k2 = k1 + 1; k2 < arr.length; k2++) {
						if (arr[k1] > arr[k2]) {
							int temp = arr[k1];
							arr[k1] = arr[k2];
							arr[k2] = temp;

							String temp1 = playercard[i][k1];
							playercard[i][k1] = playercard[i][k2];
							playercard[i][k2] = temp1;

						}
					}
				}
			}

			for (int i = 0; i < playercard.length; i++) {
				Queue<String> temp = new Queue<>();
				for (int j = 0; j < playercard[i].length; j++) {
					temp.enqueue(playercard[i][j]);
				}
				sortedcard.enqueue(temp);
			}

			return sortedcard;
		}

		public static void main(String[] args) {
			String playercard[][] = cardDistribute();

			Queue<Queue<String>> sortedcard = cardSort(playercard);
			String playername[] = { "Player 1", "Player 2", "Player 3", "Player 4" };
			int index = 0;
			while (!sortedcard.isEmpty()) {
				Queue<String> temp = sortedcard.dequeue();
				System.out.print(playername[index] + "--> ");
				while (!temp.isEmpty()) {
					System.out.print(temp.dequeue() + "  ");
				}
				System.out.println();
				index++;
			}
		}
	}
}
