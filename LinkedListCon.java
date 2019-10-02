import java.util.*;
class LinkedListCon {
	public static void main(String[] args) {
		ListNode head = new ListNode (1);
		head.next = new ListNode (2);
		head.next.next = new ListNode(3);
		ListNode node = head.next.next;
		node.next = new ListNode(4);
		node.next.next = new ListNode(5);
		print(head);
		print(removeElements(head, 2));
		print(reverse(head));
		
	}
	public static sortList(ListNode head){
		
	}
	public static void reorderList(ListNode head){
		if (head == null || head.next == null){
			return;
		}
		ListNode mid = middle(head);
		ListNode right = reverse(mid.next);
		mid.next = null;
		
		while(head != null && right != null){
			ListNode temp1 = head.next;
			ListNode temp2 = right.next;
			right.next = head.next;
			head.next = right;
			head = temp1;
			right = temp2;
		}
				
		
	}
	public static ListNode middle(ListNode head){
		ListNode fast = head.next, slow = head;
		while(fast != null && fast.next != null){
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
		
	}
	public static ListNode rotate(ListNode head, int n){
		if (head == null || head.next == null) return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode fast = dummy, slow = dummy;
		for (int i = 0; fast.next != null; i++){
			fast = fast.next;
		}
		for (int j = 0; j < i - (n % i); j++){
			slow = slow.next;
		}
		fast.next = dummy.next;
		dummy.next = slow.next;
		slow.next = null;
		return dummy.next;
	}
	public static ListNode[] splitListToParts(ListNode root, int k) {
		ListNode [] parts = new ListNode[k];
		// ListNode head = root; 
		int len = 0;
		for (ListNode head = root; head != null; head = head.next){
			len++;
		}
		int num = len / k;
		int r = len % k;
		ListNode head = root;
		ListNode prev = root;
		for (int i = 0; i < k && head != null; i++, r--){
			parts[i] = head;
			for (int j = 0; j < num + (r > 0 ? 1 : 0); j++){
				prev = head;
				head = head.next;
			}
			prev.next = null;
		}
		return parts;
	}
	public static ListNode oddEvenList(ListNode head) {
		if (head == null || head.next == null){
			return head;
		}
		ListNode odd = head;
		ListNode even = head.next;
		ListNode evenHead = even; 
		while(even != null && even.next != null){
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return head;
	}
	public static ListNode reverseKGroup(ListNode head, int k){
		ListNode dummy = new ListNode(0), start = dummy;
		dummy.next = head;
		while(true){
			ListNode p = start, c, n = p;
			start = p.next;
			for (int i = 0; i < k && n != null;i++){
				n = n.next;
			}
			if (n == null) break;
			for (int i = 0; i < k - 1; i++){
				c = p.next;
				
				p.next = c.next;
				c.next = n.next;
				n.next = c;
				
			}
		}
		return dummy.next;
		
	}
	public static ListNode reversePart(ListNode head, int m, int n){
		if (head == null || head.next == null) return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy;
		for (int i = 0; i < m - 1; i++){
			head = head.next;
		}
		ListNode cur = head.next;
		for (int i = 0;i < n - m; i++){
			ListNode temp = cur.next;
			cur.next = temp.next;
			temp.next = head.next;
			head.next = temp;
		}
		return dummy.next;
		
	}
	public static ListNode addNumber(ListNode l1, ListNode l2){
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy;
		int carry = 0;
		while(l1 != null || l2 != null){
			int x = l1 == null ? 0 : l1.val;
			int y = l2 == null ? 0 : l2.val;
			int sum = x + y + carry;
			head.next = new ListNode(sum % 10);
			carry = sum / 10;
			head = head.next;
			if (l1 != null) l1 = l1.next;
			if (l2 != null) l2 = l2.next;
			
		}
		if (carry != 0){
			head.next = new ListNode(carry);
		}
		return dummy.next;
	}
	public static ListNode swapPairs(ListNode head){
		if (head == null || head.next == null){
			return head;
		} 
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode l1 = dummy;
		ListNode l2 = head;
		while(l2 != null && l2.next != null){
			ListNode temp = l2.next.next;
			l1.next = l2.next;
			l2.next.next = l2;
			l2.next = temp;
			l1 = l2;
			l2 = temp;
		}
		return dummy.next;
		
	}
	
	public static int length(ListNode head){
		ListNode cur = head;
		int count = 0;
		while(cur != null){
			cur = cur.next;
			count++;
		}
		return count;
	}
	public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode a = headA;
		ListNode b = headB;
		int diff = length(headA) - length(headB);
		if (diff < 0){
			a = headB;
			b = headA;
		}
		for (int i = 0; i < Math.abs(diff); i++){
			a = a.next;
		}
		while(a != b){
			a = a.next;
			b = b.next;
		}
		return a;
	}
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy;
		ListNode first = head;
		for (int i = 0; i < n; i++){
			first = first.next;
		}
		while(first != null && first.next != null){
			first = first.next;
			head = head.next;
		}
		head.next = head.next.next;
		return dummy.next;
	}
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode head = dummy;
		while(l1 != null && l2 != null){
			if (l1.val <= l2.val) {
				head.next = l1;
				l1 = l1.next;
			} else {
				head.next = l2;
				l2 = l2.next;
			}
			head = head.next;
		}
		head.next = l1 == null ? l2 : l1;
			
		return dummy.next;
	}
	public static ListNode deleteDuplicates1(ListNode){
		ListNode cur = head;
		while(cur != null && cur.next != null){
			if (cur.val = cur.next.val){
				cur.next = cur.next.next;
			}else{
				cur = cur.next;
			}
		}
		return head;
	}
    public static ListNode deleteDuplicates(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        while(head != null && head.next != null){
            ListNode temp = head.next;
			while(temp.next != null && temp.val == temp.next.val){
				temp = temp.next;
			}
			if (head.next == temp){
				head = head.next;
			}else{
				head.next = temp.next;
			}
        }
		return dummy.next;
    }
	public static boolean hasCycle(ListNode head){
		if (head == null || head.next == null){
			return false;
		}
		ListNode fast = head.next;
		ListNode slow = head;
		while(slow != fast){
			if (fast == null || fast.next == null){
				return false;
			}
			slow = slow.next;
			fast = fast.next.next;
		}
		return true;
	}
	public static ListNode removeElements(ListNode head, int val){
		if (head == null) return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy;
		while(head.next != null){
			if (head.next.val == val){
				head.next = head.next.next;
			}else{
				head = head.next;
			}
		}
		return dummy.next;
	}
	public static ListNode reverse(ListNode head) {
			// if (head == null || head.next == null){
			//     return head;
			// }
		ListNode prev = null;
			
		while (head != null){
			ListNode temp = head.next;
			head.next = prev;
			prev = head;
			head = temp;
		} 
		return prev;
	}
	public static void print(ListNode head){
		while(head != null){
			System.out.print(head.val);
			System.out.print(" -> ");
			head = head.next;
		}
		System.out.println("null");
	}
}
class ListNode{
	int val;
	ListNode next;
	public ListNode(int val){
		this.val = val;
	}
}
