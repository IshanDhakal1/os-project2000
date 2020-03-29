import java.util.*;
class scheduler{
	public static void main(String[]args){
		Scanner sc=new Scanner(System.in);
		int n,i,j,b_t=0;
		double avg_waiting=0.0, avg_turnaround=0.0;
		System.out.println("Enter the no. of processes: ");
		do{
			n=sc.nextInt();
			if(n<0){
				System.out.println("Try Again");
			}
		}while(n<0);
		int no[]=new int[n];
		int arrival[]=new int[n];
		int burst[]=new int[n];
		int completion[]=new int[n];
		for(i=0;i<n;i++){
			no[i]=i;
			System.out.println("Enter Details For P"+no[i]+" process:-\n");
			System.out.println("Enter Arrival Time: ");
			arrival[i]=sc.nextInt();
			System.out.println("Enter Burst Time: ");
			burst[i]=sc.nextInt();
			b_t = b_t + burst[i];
		}
		for(i=0;i<n-1;i++){
			for(j=i+1;j<n;j++){
				if(arrival[i]>arrival[j]){
					int pno=no[i];
					int a=arrival[i];
					int b=burst[i];
					no[i]=no[j];
					arrival[i]=arrival[j];
					burst[i]=burst[j];
					no[j]=pno;
					arrival[j]=a;
					burst[j]=b;
				}
			}
		}
		int ct=0;
		for(i=0;i<n;i++){
			if(i==0&&arrival[i]!=0){
				ct=arrival[i]+ct+burst[i];
			}else if(arrival[i]>completion[i-1]){
				ct=arrival[i]-completion[i-1]+ct+burst[i];
			}else{
				ct=ct+burst[i];
			}
			completion[i]=ct;
		}
		int tat[]=new int[n];
		int wt[]=new int[n];
		for(i=0;i<n;i++){
			tat[i]=completion[i]-arrival[i];
			avg_turnaround+=tat[i];
			wt[i]=tat[i]-burst[i];
			avg_waiting+=wt[i];
		}
		avg_waiting=avg_waiting/n;
		avg_turnaround=avg_turnaround/n;
		System.out.println("PNO\tAT\tCT\tTA\tWTt\n");
		for(i=0;i<n;i++){
			System.out.println("P"+no[i]+"\t"+arrival[i]+"\t"+completion[i]+"\t"+tat[i]+"\t"+wt[i]+"\n");
		}
		System.out.println("Average Turn around Time:  "+avg_turnaround+"\n\n");
		System.out.println("Average Waiting Time :  "+ avg_waiting+"\n");
	}
}
