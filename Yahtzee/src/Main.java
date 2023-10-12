import java.util.*;
import java.io.*;
public class Main {
	public static boolean yahtzee(int[][] yahtzeeCounts) {//Cheks if there is a yahtzee and sets score and deletes nubers
		boolean flag = false;
		for(int i =0; i< yahtzeeCounts.length;i++) {//Removes four same numbers
			for(int j =0; j<yahtzeeCounts[i].length;j++) {
				if(yahtzeeCounts[i][j] >=4)
					if(i==0) {
						flag= true;
						for(int k = 0; k<4;k++) {
							playerOne.getCombination().remove(j+1);//Number delete
							yahtzeeCounts[i][j]--;
						}
						playerOne.setScore(playerOne.getScore()+10);//Adding score
					}
					else {
						flag = true;
						for(int k = 0; k<4;k++) {
							playerTwo.getCombination().remove(j+1);
							yahtzeeCounts[i][j]--;
						}
						playerTwo.setScore(playerTwo.getScore()+10);
					}				
							
			}
		}
		return flag;//To know if there is a yahtzee
	}
	
	public static boolean largeStraight(int[][] yahtzeeCounts) {//Cheks if there is a large straight and sets score and deletes numbers
		boolean flag = false;
		if(playerOne.getCombination().search(1)&&playerOne.getCombination().search(2)&&playerOne.getCombination().search(3)
				&&playerOne.getCombination().search(4)&&playerOne.getCombination().search(5)&&playerOne.getCombination().search(6)) {
			flag = true;
			for(int i = 1; i<=6;i++){
				playerOne.getCombination().remove(i);//Removing numbers
				yahtzeeCounts[0][i-1]--;
			}
			playerOne.setScore(playerOne.getScore()+30);//Adding score
		}
		if(playerTwo.getCombination().search(1)&&playerTwo.getCombination().search(2)&&playerTwo.getCombination().search(3)
				&&playerTwo.getCombination().search(4)&&playerTwo.getCombination().search(5)&&playerTwo.getCombination().search(6)) {
			flag = true;
			for(int i = 1; i<=6;i++) {
				playerTwo.getCombination().remove(i);
				yahtzeeCounts[1][i-1]--;
			}
			playerTwo.setScore(playerTwo.getScore()+30);
		}
		return flag;//To know if there is large straight
		
	}
	
	public static void gameScreen() {//Display screen
		System.out.print(playerOne.getName()+":  ");
		playerOne.getCombination().display();
		System.out.println("\t score:"+playerOne.getScore());
		System.out.print(playerTwo.getName()+":  ");
		playerTwo.getCombination().display();
		System.out.println("\t score:"+playerTwo.getScore()+"\n\n");
	}
	
	public static void scoreTable(SingleLinkedList scoreTable ) throws Exception {//Adding file to new player and then adding them to the list
		File file = new File("HighScoreTable.txt");
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
		out.print("\n"+playerOne.getName()+"\n" + playerOne.getScore());//Adding to file
		out.print("\n"+playerTwo.getName()+"\n" + playerTwo.getScore());
		out.close();
		Scanner scan = new Scanner(file);
		while(scan.hasNextLine()) {//Readingand adding players to list (first line name second line score)
			Player player = new Player();
			player.setName(scan.nextLine());
			player.setScore(Integer.valueOf(scan.nextLine()));
			scoreTable.addIncreasingInt(player);
			
		}
		scan.close();
	}
	
	static Player playerOne = new Player();//Initalizing player lists as static
	static Player playerTwo = new Player();
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int dice = 0, turn = 1;//Dice holds the random number
		SingleLinkedList playerOneComb= new SingleLinkedList();//List for holding players' numbers
		SingleLinkedList playerTwoComb= new SingleLinkedList();
		SingleLinkedList scoreTable = new SingleLinkedList();//list for score table
		
		int[][] yahtzeeCounts= new int[2][6];//Counts numbers [0][x] for player one [1][x] for player two//[x][0]th index for nmber ONE [x][1] index for number TWO and so on
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);		
		
		
		
		System.out.println("Please enter the first player's name");
		playerOne.setName(sc.nextLine());	playerOne.setScore(0);	playerOne.setCombination(playerOneComb);//Sets player's name scoer and list 
		System.out.println("Please enter the first player's name");
		playerTwo.setName(sc.nextLine());	playerTwo.setScore(0);	playerTwo.setCombination(playerTwoComb);
		sc.close();
		
		
		while(turn<=10) {//Turn based game
				for(int i = 0; i< 3;i++) {//Random three dice for player1
					dice = rand.nextInt(1,7);
					yahtzeeCounts[0][dice-1]++;//Increases the count of number to determine how many of them
					playerOne.getCombination().addLast(dice);			
				}
			
				for(int i = 0; i< 3;i++) {//Random three dice for player2
					dice = rand.nextInt(1,7);
					yahtzeeCounts[1][dice-1]++;//Increases the count of number to determine how many of them
					playerTwo.getCombination().addLast(dice);					
				}
				System.out.println("Turn" + turn);//Printing the players' list after random dices
				gameScreen();
				if(largeStraight(yahtzeeCounts)|| yahtzee(yahtzeeCounts)) {//Prints again after yahtzee or large straight
					gameScreen();
				}	
			turn++;
			Thread.sleep(250);
		}
		System.out.println("Game is Over!\n");
		if(playerOne.getScore()>playerTwo.getScore())//Statements to determine winner
			System.out.println("Winner is " + playerOne.getName()+"\n");
		else if(playerOne.getScore()<playerTwo.getScore())
			System.out.println("Winner is " + playerTwo.getName()+"\n");
		else
			System.out.println("DRAW!!!"+"\n");
		scoreTable(scoreTable);//Reads file and initializes scoretable list
		System.out.println("High Score Table");
		scoreTable.displayScoreTable();//Displaying score table

	}

}
