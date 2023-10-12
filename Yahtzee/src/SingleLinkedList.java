
public class SingleLinkedList {
	Node head;
	
	public void addLast(Object data) {
		if(head == null) {
			Node newNode = new Node(data);
			head = newNode;
		}else {
			Node temp = head;
			while(temp.getLink() != null) {
				temp = temp.getLink();
			}
			Node newNode = new Node (data);
			temp.setLink(newNode);
		}
	}
	public void addIncreasingInt(Object data) {
		if(head == null) {
			Node newNode = new Node(data);
			head = newNode;
		}else if(((Player)head.getData()).getScore()< ((Player) data).getScore()){
			Node newNode = new Node(data);
			newNode.setLink(head);
			head = newNode;
			
		}
		else {
			Node temp = head;
			while(temp.getLink()!=null && ((Player)temp.getLink().getData()).getScore() >= ((Player) data).getScore()) {
				temp = temp.getLink();
			}
			Node newNode = new Node(data);
			newNode.setLink(temp.getLink());
			temp.setLink(newNode);
		}
	}
	public int size() {
		if(head == null) {
			return 0;
		}else {
			int count = 0;
			Node temp = head;
			while(temp != null) {
				temp = temp.getLink();
				count++;
			}
			return count;
		}
		
	}
	public void display() {
		if(head == null) {
			System.out.println("List is empty!");
		}else {
			Node temp = head;
			while(temp != null) {
				System.out.print(temp.getData() + " ");
				
				temp = temp.getLink();
			}
		}
	}
	public void displayScoreTable() {//For writing topten players and deleting the players' list data
		if(head == null) {
			System.out.println("List is empty!");
		}else {
			int count = 1;
			Node temp = head;
			while(temp != null && count <=10) {//Writes top ten
				System.out.print(((Player)temp.getData()).getName() + " " + ((Player)temp.getData()).getScore()+"\n");	
				temp = temp.getLink();
				count++;
			}
			while(size()>0) {
				temp = head;
				removeFromTable(temp.getData());
			}

		}
	}
	public void removeFromTable(Object dataToDelete) {//Remove operation for score table
		if(head == null)
			System.out.println("Linked list is empty");
		else {
			if(((Player) head.getData()) == ((Player) dataToDelete)) 
				head = head.getLink();
			
			else {
				Node previous = null;
				Node temp = head;
				while(temp != null) {
					if(((Player) temp.getData()) == ((Player) dataToDelete)) {
						previous.setLink(temp.getLink());
						temp = previous;
						break;
						
					
					}
					previous = temp;
					temp = temp.getLink();
				}
			}
			
		}
		
	}
	
	public void remove(Object dataToDelete) {
		if(head == null)
			System.out.println("Linked list is empty");
		else {
			if((int) head.getData() == (int) dataToDelete) 
				head = head.getLink();
			
			else {
				Node previous = null;
				Node temp = head;
				while(temp != null) {
					if((int) temp.getData() == (int) dataToDelete) {
						previous.setLink(temp.getLink());
						temp = previous;
						break;
						
					
					}
					previous = temp;
					temp = temp.getLink();
				}
			}
			
		}
		
	}

	public boolean search(Object data) {
		if(head == null) {
			System.out.println("List is empty");
			return false;
		}
		else {
			Node temp = head;
			while(temp != null) {
				if((Integer) temp.getData() == (Integer)data) {
					return true;
					
				}
				temp = temp.getLink();
			}
			return false;
		
		}
	}

	

}
