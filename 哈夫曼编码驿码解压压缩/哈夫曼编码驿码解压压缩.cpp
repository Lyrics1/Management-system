#include<stdio.h>
#include<stdlib.h>
#include <string.h>
#include<math.h>
#define MAX 256
#define ZMAX 2000
#define NMAX 30
typedef struct {
	char data;
	int weight;
	int parent,Rchild,Lchild;
	int tag;
} HfNode,hf[ZMAX];
typedef char* hfcode[MAX];//�������Ͷ���

//��������

int Read(char name[],int znum[],hf h);
void creathf(hf h,int n);
void select(hf h,int n,int *s1,int *s2);
void translation(hf h,int n,hfcode hc,char name[],char recodename[]);

void creathuffCode1(hf h,hfcode hc,int n);
void printnum(hfcode hc,hf h,int n,char name[],char translation[],char codename[]);
void  conv(char c[],char jiecomname[],char codename[] );




//���ļ�
int Read(char name[],int znum[],hf h) {

	char buffer;
	int i,j,count=0,fileLen=0,tagnum=0;
	HfNode temp;
	FILE *infile,*outfile;
	infile=fopen(name,"rb");
	//�ж��ļ��Ƿ����
	if(infile==NULL) {
		printf("�ļ������ڣ�");
		return-1;
	}
	for(i=0; i<MAX; i++) //��ʼ�����
		h[i].tag=-1;


	//�ȸ�buff����ռ�
	fread((char*)&buffer,sizeof(char),1,infile);
	while(!feof(infile)) { //�ж��ļ��Ƿ������0��ʾû�н�������0��ʾ����
		znum[buffer]++;
		if(h[buffer].tag==-1) {
			tagnum++;
			h[buffer].tag=tagnum;
		}
		fileLen++;
		h[buffer].data=buffer;
		h[buffer].weight++;
		//	printf("%c,%d\n",buffer,buffer-'a');//����������ַ�
		if(buffer<0)
			printf("��������");
		fread((char*)&buffer,sizeof(char),1,infile);
	}


	fclose(infile);
	//��Ƶ��Ϊ0���ַ��ŵ�����
	for(i=0; i<MAX-1; i++)
		for(j=i+1; j<MAX; j++) {
			if(h[i].weight<h[j].weight) {
				temp=h[i];
				h[i]=h[j];
				h[j]=temp;
			}
		}
	//������������Ҳ���ǹ������ڵ��ʼ������2�ַ�����
	for(i=0; i<MAX; i++)
		if(h[i].weight!=0) {
			count++;
			//printf("%c,%d,%d,%d\n",h[i].data ,h[i].data -'a',i,h[i].weight);
		}

//		for(i=0;i<MAX;i++)
//		  	if(h[i].weight==0)
//		  		break;
//		count=i; //i���ǽڵ�������Ϊ�ղ��Ѿ��ź�����
//		int hufnum=2*count+1;//�������������ڵ���
//	printf("read end\n");
	//	printf("%c,%d,%d,%d",h[1].data ,h[1].data -'a',1,h[1].weight);
	return count;
}


//��ʼ������������
void creathf(hf h,int n) {
	int weight;
	int m=2*n-1;
	int i,temp;
	int s1,s2;

	for(i=n; i<=m; i++) {
		select(h,i-1,&s1,&s2);

		temp=h[s1].weight+h[s2].weight;

		h[i].weight=temp;

		h[i].Lchild=s1;
		h[i].Rchild=s2;
		h[s1].parent=h[s2].parent=i;

	}
}

void select(hf h,int n,int *s1,int *s2) {
	int i,j;
	int x1=0,x2=0;
	int m1 = 1000;
	int m2 = 1000;
	for (j=1; j<=n; j++) {
		if (h[j].weight < m1 && h[j].parent==0) {
			m2=m1;
			x2=x1;
			m1=h[j].weight;
			x1=j;
		} else if (h[j].weight < m2 && h[j].parent==0) {
			m2=h[j].weight;
			x2=j;
		}
	}
	*s1=x1;
	*s2=x2;

}

void output(hf h,int n) {
	int i=1;
	for(i=0; i<2*n-1; i++) {
		printf("%3d   p:%3d L��%d R:%d\n",h[i].weight,h[i].parent,h[i].Lchild,h[i].Rchild);
	}


}
//����������

void creathuffCode1(hf h,hfcode hc,int n) {
	char *cd;
	int start,p,i,c;
	cd=(char *)malloc(sizeof(char)*n);
	cd[n-1]='\0';
	for(i=0; i<n; i++) {
		start=n-1;
		c=i;
		p=h[i].parent;
		while(p!=0) {
			--start;
			if(h[p].Lchild==c)
				cd[start]='0';
			else cd[start]='1';
			c=p;
			p=h[p].parent;
		}
		hc[i]=(char *)malloc(sizeof(char)*(n-start));
		strcpy(hc[i],&cd[start]);
	}

	free(cd);
}
//��ȡ�����ַ�������
//����
void printnum(hfcode hc,hf h,int n,char name[],char translation[],char codename[]) {
	int i,j,k=1,t;
	char buffer;
	FILE *infile,*outfile,*codefile;
	infile=fopen(name,"rb");
	//�ж��ļ��Ƿ����
	if(infile==NULL) {
		printf("�ļ������ڣ�");
		return;
	}
	fread((char*)&buffer,sizeof(char),1,infile);
	while(!feof(infile)) { //�ж��ļ��Ƿ������0��ʾû�н�������0��ʾ����
		for(i=0; i<n; i++)
			if(h[i].data==buffer) {
//				printf("%s",hc[i]);
				//	printf("%c",h[i].data);
				strcat(translation,hc[i]);
			}

		fread((char*)&buffer,sizeof(char),1,infile);
	}
	fclose(infile);
	//�������,�½��ļ���Ϊcode.txt
	int L=strlen(translation);
	i=0;
	codefile=fopen(codename,"wb");
	if(codefile==NULL) {
		printf("�ļ������ڣ�");
		return;
	}
	while(i<L-1) {
		fwrite(&translation[i],sizeof(char),1,codefile);
		i++;
	}
	fclose(codefile);
}
//����
void translation(hf h,int n,hfcode hc,char name[],char recodename[]) {
	char buffer,a[MAX];
	int i=0,j,p,t;
	FILE *infile,*outfile,*Recodefile;
	infile=fopen(name,"rb");
	//�ж��ļ��Ƿ����
	if(infile==NULL) {
		printf("�ļ������ڣ�");
		return;
	}
	fread((char*)&buffer,sizeof(char),1,infile);
	a[i]=buffer;
	i++;
	while(!feof(infile)) { //�ж��ļ��Ƿ������0��ʾû�н�������0��ʾ����
		fread((char*)&buffer,sizeof(char),1,infile);
		a[i]=buffer;
		i++;
	}

	fclose(infile);
	j=strlen(a);
//	printf("\nj:%d",j);
	Recodefile=fopen(recodename,"wb");
	if(Recodefile==NULL) {
		printf("�ļ������ڣ�");
		return;
	}
	a[j]='\0';
	p=2*n-1;
	for(i=0; i<=j; i++) {

		if(a[i]=='0')
			p=h[p].Lchild;
		if(a[i]=='1')
			p=h[p].Rchild;
		if(h[p].Lchild==0&&h[p].Rchild==0) {

			t=p;
//			printf("%c",h[t].data);
			fwrite(&h[t].data,sizeof(char),1,Recodefile);
			p=2*n-1;
		}

	}
	fclose(Recodefile) ;

}
// ѹ��
void compress(char codename[],char compressname[]) {
	unsigned char buffer;
	int count=1,i=0,j,sum=0;
	char temp[ZMAX],tcode[9];//��ΪҪ����8bitѹ��
	FILE *filecode,*outfile;
	filecode=fopen(codename,"rb");
	outfile=fopen(compressname,"wb");

	if(outfile==NULL||filecode==NULL) {
		printf("�ļ������ڣ�");
		return;
	}
	i=0;
	fread((unsigned char*)&buffer,sizeof(char),1,filecode);
//	temp[i]=buffer;
//	i++;
	while(!feof(filecode)) {

		temp[i]=buffer;
		i++;
		fread((unsigned char*)&buffer,sizeof(char),1,filecode);
	}

	fclose(filecode);
	sum=0;
	j=strlen(temp);
	//	printf("%d\n",strlen(temp));

	while(j>=8) {
		for(i=0; i<8; i++) {
			if(temp[i]=='1')//������ѹ��
				sum+=pow(2,8-i-1);
//					temp <<= 1;
		}
//				printf("\n%d",sum);
		//	sprintf(tcode,"%d",sum);//����ת��Ϊ�ַ�����ΪҪ�洢Ϊ�������ļ�
//				printf("%c",sum);
		fwrite((char*)&sum,sizeof(char),1,outfile);
		strcpy(temp,temp+8);//����ǰ8λ
		j=strlen(temp);
		sum=0;
	}

	//������󼸸���bu����8λ���ַ�
	j=strlen(temp);
//			printf("\nj:%d\n,%s",j,temp);
	int k=0;
	sum=0;
	while(k<j&&j>0) { //00001001
		if(temp[k]=='1')//������ѹ��
			sum+=pow(2,j-k-1);
		//	temp<<=1;
		k++;
	}
 
			printf("%d\t%c\n",sum,sum);

//			sprintf(tcode,"%d",sum);
	fwrite((char*)&sum,sizeof(char),1,outfile);
	fclose(outfile);
}
//��ѹ
void jiecompress(char compressname[],char jiecomname[],char codename[]) {
	FILE *infile,*outfile;
	int temp,i,j,k,codetemp,o,tag;
	char tcode[30],buffer;
	outfile=fopen(jiecomname,"wb");
	infile=fopen(compressname,"rb");
	if(outfile==NULL||infile==NULL) {
		printf("�ļ��򿪴���\n");
		return;
	}
	int p=0;
	fread(&buffer,sizeof(char),1,infile);//��洢�ļ�ʱ����һ��
//	int fno = fileno(infile);  //
//	int fsize = filelength(fno);   //
	int fsize=ZMAX;
	char hanzi[fsize];
	while(!feof(infile)) {
		fsize++;
		hanzi[p++] = buffer;
		fread((char*)&buffer,sizeof(char),1,infile);

	}

	hanzi[p] = '\0';
	conv(hanzi,jiecomname,codename);

	fclose(infile);
	fclose(outfile);
}
//������
void  conv(char c[],char jiecomname[],char codename[] )
 {
	FILE *outfile,*infile;
	char tcode[30],buffer;
	int temp=0,mod=0;

	outfile=fopen(jiecomname,"wb");
	infile = fopen(codename,"rb");
	if(outfile==NULL || infile==NULL)
		return;
	fread((char*)&buffer,sizeof(char),1,infile);
	while(!feof(infile))	
	{
		temp++;
		fread((char*)&buffer,sizeof(char),1,infile);	
	}
	fclose(infile);
	mod=temp%8;
//	printf("%d",strlen(c));
	char ch[9] = {'\0'};

	char h[20];
	int i;
	int j = 0;
	int count = 0;
	char t[3] = {'\0'};
	while('\0' != c[j]) {
		for(i = 7; i >= 0; i--) {
			ch[i] = (c[j]&1)+'0';
			//����
			c[j]>>=1;
		}
		//strcat(h,ch);
		j++;
		fwrite(&ch,sizeof(char),8,outfile);
	}
	itoa((int)(c[j]),ch,2);
	temp=j=strlen(ch);
//	printf("j:%dmod��%d\n",j,mod);

	for(i=mod-1;i>=0,j>0;i--)
	{
		ch[i]=ch[j-1];//10  0010
		j--;	
	} 
	for(i=0;i<temp;i++)
	{
		ch[i]='0';
	}
	ch[mod] ='\0';
//	for(i=0;i<)
	
//	printf("%s%d",ch,strlen(ch));
	
	fwrite(&ch,sizeof(char),strlen(ch),outfile);
	fclose(outfile);

}

int main(void) {
	int znum[MAX]= {0}; //ÿ���ַ����ֵĴ���
	char s[ZMAX];//������ַ�
	char name1[MAX];//�ļ���
	int n;//��ʼ�ڵ���
	hf h;char b[MAX];
	char codename[10],recodename[10],compressname[10],jiecomname[10];
	printf("\t\t================�ҵĹ�������ѹѹ��==================\n\n"); 
	printf("\t\t\t\t1:����\n\t\t\t\t2:����\n\t\t\t\t3:ѹ��\n\t\t\t\t4:��ѹ\n\t\t\t\t0:�˳�\n\n");
	printf("\t\t����������Ҫ��ѹ���ļ��������磺z.txt :");
	gets(name1);
	hfcode hc;
	int chance;
		//��ʼ���ļ�Ȼ��洢��������ʼ�ڵ㣻
	n=Read(name1,znum,h);
//	printf("n:%d\n",n);
	creathf(h,n);
	//output(h,n);//���������������ȷ��
	creathuffCode1(h,hc, n);

	printf("���������ѡ��"); 
	scanf("%d",&chance);
	while(chance!=0)
	{
		switch(chance)
		{
			case 1: printf("\n����Ҫ�洢������ļ�����code.txt\n");
			getchar();
//					gets(codename);
					printnum(hc,h,n,name1,b,"code.txt");break;
			case 2:	printf("\n����Ҫ�洢�������ļ�����recode.txt\n");
			getchar();
			printnum(hc,h,n,name1,b,"code.txt");
//					gets(recodename);
					translation(h,n,hc,"code.txt","recode.txt");break;
			case 3:	printf("\n����Ҫ�洢ѹ���ļ����ļ�����:stor.txt\n");
			getchar();
			printnum(hc,h,n,name1,b,"code.txt");
			translation(h,n,hc,"code.txt","recode.txt");
//					gets(compressname);
					compress("code.txt", "stor.txt");break;
			case 4: printf("\n����Ҫ��ѹ�洢���ļ�����jie.txt\n");
			getchar();
			printnum(hc,h,n,name1,b,"code.txt");
			translation(h,n,hc,"code.txt","recode.txt");
			compress("code.txt", "stor.txt");
//					gets(jiecomname);
					jiecompress("stor.txt","jie.txt","code.txt") ;
		}
			printf("\n���������ѡ��"); 
		scanf("%d",&chance);
	}

	
	
	
	
}





