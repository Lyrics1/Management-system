#include <stdio.h>

#include "Main_Menu.h"
#include "Schedule_UI.h"
#include "Studio_UI.h"
#include "Play_UI.h"
#include "Queries_Menu.h"
#include "Account_UI.h"
#include "Sale_UI.h"
#include "SalesAnalysis_UI.h"

void Main_Menu(void) {
	char choice;
	int i;
	do {
		printf("\n  ==================================================================\n\n");
		printf("**************** Theater Ticket Management System ****************\n\n");
		printf("**************** ��ԺƱ�����ϵͳ ****************\n\n");
		//printf("[S]tudio Management.\n");
		printf("[S]�ݳ�������.\n\n");
	//	printf("[P]lay Management.\n");
		printf("[P]��Ŀ����.\n\n");
	//	printf("[T]icket Sale.\n");
		printf("[T]��Ʊ����.\n\n");
	//	printf("[R]eturn Ticket.\n");
		printf("[R]��Ʊ����.\n\n");
	//	printf("[Q]ueries\n");
		printf("[Q]��ѯ�˵�.\n\n");
	//	printf("Ra[n]king and Statistics.\n");
		printf("Ra[n]��ҳ��ʾ.\n\n");
	//	printf("[A]ccount Management.\n");
		printf("[A]ϵͳ�û�����.\n\n");
	//	printf("[E]xist.\n");
		printf("[E]�˳�ϵͳ.\n\n");
		printf("\n==================================================================\n");
		printf("Please input your choice:");
		fflush(stdin);
		choice = getchar();
		switch (choice) {
		case 'S':
		case 's':
			Studio_UI_MgtEntry();
			break;
		case 'P':
		case 'p':
			do{printf("\n0:��Ŀ�������\t 1 :��Ŀ��ѯ����\n ");
			scanf("%d",&i);
			if(i!=0 || i!=1)
				printf("������������������!!\n");
			}while(i!=0 || i!=1);
			fflush(stdin);
			Play_UI_MgtEntry(i);
			break;
		case 'Q':
		case 'q':
			Queries_Menu();
			break;
		case 'T':
		case 't':
			Sale_UI_MgtEntry();
			break;
		case 'R':
		case 'r':
			Sale_UI_ReturnTicket();
			break;
		case 'N':
		case 'n':
			SalesAanalysis_UI_MgtEntry();
			break;
		case 'A':
		case 'a':
			Account_UI_MgtEntry();
			break;
		}
	} while ('E' != choice && 'e' != choice);
}

