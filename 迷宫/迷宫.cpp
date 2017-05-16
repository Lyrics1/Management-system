	#include<stdio.h>
	#include<stdlib.h>
	#include<time.h>
	#define M 6
	#define N 6
	
	typedef struct stacklost
	{
		int logo[M][N];
	}*stackList;
	
	 struct 
	{
		int x;
		int y;
		int pre;
	}Load[100]; 
	
	//�������� 
	stackList instack();
	int run(int row1,int col1,int row2,int col2,stackList s);
	void output(int front);
	void print(stackList s);
	
	stackList instack()
	{
		 int i,j;
			stackList s;
			s=(stackList)malloc(sizeof(stackList));			         
		if(s==NULL)                            
		srand((unsigned)time(NULL));//��ʱ�������ӣ�ÿ�β����������һ�� 
		for(i=0;i<M;i++)            
		{	
			for(j=0;j<N;j++)
			{	
				s->logo[i][j]=rand()%2;//����0 �� 1���������	
			}	
			s->logo[i][(rand()%4)+1]=0;	 //��֤�϶���ͨ·�� 
	
		}
	// ��Χ 	
		for(i=0;i<M;i++)
		{
			s->logo[i][0]=1;
			s->logo[i][N-1]=1;
		 }
		 for(j=0;j<N;j++)
		 {
		 	s->logo[0][j]=1;
		 	s->logo[M-1][j]=1;
		  } 	  
		return s;	
	}
	
	int run(int row1,int col1,int row2,int col2,stackList s)
	{
		int i,j,tag=0,four;
		int rear=-1,front=-1;
		rear++;
		Load[rear].x=row1;
		Load[rear].y=col1;
		Load[rear].pre=-1;
		s->logo[row1][col1]=-1;
	
		while(front<=rear && !tag)
		 {	front++;
		 	i=Load[front].x;// ��¼���ڵ�����꣬ 
		 	j=Load[front].y;
		 	if(i==row2 && j==col2)//���������±��ж��Ƿ��ǳ��� 
		 	{
		 		output(front);
		 		return 1;
			 }
	
			 for(four=0;four<4;four++)//�ĸ�������ж�ִ�� 
			 {
				switch(four)
			 	{
			 		case 0:i=Load[front].x-1;j=Load[front].y;break;//�� 
			 		case 1:j=Load[front].x+1;j=Load[front].y;break;//�� 
			 		case 2:i=Load[front].x;j=Load[front].y+1;break;//�� 
			 		case 3:j=Load[front].x;j=Load[front].y-1;break;//�� 
				 }
	
				 if(s->logo[i][j]==0 && i>=0&&i<M && j>=0&&j<N)
				 {
				 	rear++;
				 	Load[rear].x=i;//�洢 
				 	Load[rear].y=j;
				 	Load[rear].pre=front;//�����һ�������λ�� 
				 	s->logo[i][j]=-1;//���Ա�֤����ԭ����λ������Ч�� 
				 }
			 }
		 }	
		 printf("\t\terror  not load to break");
			return 0;
		}
	
		void output(int front)
		{
		//	printf("%d",front); 
			int k=front,j,m=1;
			do//���߹���·�� pre=-1��  ��ǰ�� pre  ��¼�Ķ���ǰһ������±� 
			{
				j=k;
				k=Load[k].pre;
				Load[j].pre=-1;
			}while(k!=0);
			printf("\n\t\tthe lost load: ");
			k=0;//��ʼ��������� 
			while(k<M)
			{
				if(Load[k].pre==-1)
					{
						printf("(%d,%d)  ",Load[k].x,Load[k].y);
					}
					k++;
				}
			printf("\n");	
	
		}
	
	 void print(stackList s) 
	 {
	 	printf("\t\t\t�Թ�ͼ��\n"); 
		int i,j;
	 	for(i=0;i<M;i++)
	 	{	printf("\t\t\t");
	 		for(j=0;j<N;j++)
	 		{	
			 	if((i==0||i==M-1))
				{
					printf("---");
				}
				else if(j==0 || j==N-1 )
					printf("|  ");
				else if(s->logo[i][j]==1)
					printf("1  ");
				else printf("0  ");
			}
			printf("\n");
		 }
	 }
	
	 int main()
	 {
		stackList s;
	 	s=instack();
	 	print(s);
		int row1,row2,col1,col2;
		printf("\n\t\tenter the row1 col(������꣩row2 col2���������꣩\n\t\t�������룺3 3 1 4\n\t\tplease Enter:");//����ͼ���Լ�����ڻ��г��� 
		scanf("%d%d%d%d",&row1,&col1,&row2,&col2);
		run(row1,col1,row2,col2,s);
	 }
	
	
	

