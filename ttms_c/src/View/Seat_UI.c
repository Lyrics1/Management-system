/*
 *  Seat_UI.c
 *
 *  Created on: 2015��5��23��
 *  Author: lc
 */
#include "Seat_UI.h"

#include "../Service/Seat.h"
#include "../Service/Studio.h"
#include "../Service/EntityKey.h"
#include "../Common/list.h"
#include <stdio.h>

//����״̬���ض�Ӧ��ʾ״̬����
inline char Seat_UI_Status2Char(seat_status_t status) {

	char statusChar;
	switch (status) {
	case SEAT_GOOD:		//����λ
		statusChar = '#';
		break;
	case SEAT_BROKEN:	//�𻵵���λ
		statusChar = '~';
		break;
	case SEAT_NONE:
	default:
		statusChar = ' ';
		break;
	}
	return statusChar;
}

//����״̬���ŷ�����λ״̬
inline seat_status_t Seat_UI_Char2Status(char statusChar) {
	seat_status_t status;
	switch (statusChar) {
	case '#':		//����λ
		status = SEAT_GOOD;
		break;
	case '~':	//�𻵵���λ
		status = SEAT_BROKEN;
		break;
	default:
		status = SEAT_NONE;
		break;
	}
	return status;
}

void Seat_UI_MgtEntry(int roomID) {
	int i, j;
	char choice;
	int seatCount;
	int changedCount = 0;
	studio_t studioRec;

	if (!Studio_Srv_FetchByID(roomID, &studioRec)) {  //��ö�Ӧid��ӳ������Ϣ
		printf("The room does not exist!\nPress [Enter] key to return!\n");
		getchar();
		return;
	}

	seat_list_t list;
	seat_node_t *p;

	List_Init(list, seat_node_t);
	//ѡ���ӳ����������λ
	seatCount = Seat_Srv_FetchByRoomID(list, roomID);

	if (!seatCount) {		//����ӳ����û��������λ�����Զ�������λ
		seatCount = Seat_Srv_RoomInit(list, roomID, studioRec.rowsCount,
				studioRec.colsCount);
		//�޸��ݳ��������λ����
		studioRec.seatsCount = seatCount;
		Studio_Srv_Modify(&studioRec);
	}
	do {

		//system("cls");
		printf("\n==================================================================\n");
		printf("********************  Room %d Seat List  **************************\n",
				roomID);

		printf("%5c", ' ');
		for (i = 1; i <= studioRec.colsCount; i++) {
			printf("%3d", i);
		}
		printf(
				"\n------------------------------------------------------------------\n");
		//��ʾ����
		for (i = 1; i <= studioRec.rowsCount; i++) {
			j = 1;
			printf("%d��:%c", i, ' ');

			List_ForEach(list,p)
			{
				if (p->data.row == i) {
					while (p->data.column != j) {
						printf("%3c", ' ');
						j++;
					}
					printf("%3c", Seat_UI_Status2Char(p->data.status));
					j++;
				}
			}
			printf("\n");
		}

		printf("[A]dd|[D]elete|[U]pdate | [R]eturn:");
		fflush(stdin);
		scanf("%c", &choice);
		fflush(stdin);

		switch (choice) {
		case 'a':
		case 'A':
			changedCount = Seat_UI_Add(list, roomID, studioRec.rowsCount,
					studioRec.colsCount);
			if (changedCount > 0) {
				seatCount += changedCount;
				//�޸��ݳ��������λ����
				studioRec.seatsCount = seatCount;
				Studio_Srv_Modify(&studioRec);
			}
			break;
		case 'd':
		case 'D':
			changedCount = Seat_UI_Delete(list, studioRec.rowsCount,
					studioRec.colsCount);
			if (changedCount > 0) {
				seatCount -= changedCount;
				//�޸��ݳ��������λ����
				studioRec.seatsCount = seatCount;
				Studio_Srv_Modify(&studioRec);
			}
			break;
		case 'u':
		case 'U':
			Seat_UI_Modify(list, studioRec.rowsCount, studioRec.colsCount);
			break;
		}
	} while (choice != 'r' && choice != 'R');

	//�ͷ�����ռ�
	List_Destroy(list, seat_node_t);
}

int Seat_UI_Add(seat_list_t list, int roomID, int row, int column) {  //����һ����λ
	seat_t rec;
	seat_node_t *p;
	int newRecCount = 0;
	char choice;
	do {
		/*system("cls");*/
		printf(
				"\n==================================================================\n");
		printf(
				"***********************  Add New Seat  ***************************\n");
		printf(
				"------------------------------------------------------------------\n");

		do {
			printf(
					"Row shouldn't great than %d and Column shouldn't great than %d!\n",
					row, column);
			printf("Row of the Seat:");
			scanf("%d", &(rec.row));
			printf("Column of the Seat:");
			scanf("%d", &(rec.column));
			fflush(stdin);
		} while (rec.row > row || rec.column > column);

		p = Seat_Srv_FindByRowCol(list, rec.row, rec.column);
		if (p) {						//����������к�����Ӧ����λ�Ѵ��ڣ����ܲ���
			printf("The seat is already exist! \n");
			continue;
		}

		rec.id = EntKey_Srv_CompNewKey("Seat");		//������λid
		rec.roomID = roomID;
		rec.status = SEAT_GOOD;    //���������λ��״̬Ĭ��Ϊ����λ
		printf(
				"==================================================================\n");

		if (Seat_Srv_Add(&rec)) {
			newRecCount++;
			printf("The new seat added successfully!\n");
			p = (seat_node_t*) malloc(sizeof(seat_node_t));
			p->data = rec;
			Seat_Srv_AddToSoftedList(list, p); //������������λ�������list
		} else
			printf("The new seat added failed!\n");
		printf(
				"------------------------------------------------------------------\n");
		printf("[A]dd more, [R]eturn:");
		fflush(stdin);
		scanf("%c", &choice);
	} while ('a' == choice || 'A' == choice);
	return newRecCount;
}

int Seat_UI_Modify(seat_list_t list, int row, int column) {
	int rtn = 0;
	int newrow, newcolumn;
	char choice;
	seat_node_t *p;

	printf(
			"\n==================================================================\n");
	printf(
			"***************************  Update Seat  ************************\n");
	printf(
			"------------------------------------------------------------------\n");

	do {
		do {				//���µ���λ������Ϣ���ܳ�����ӳ����������
			printf(
					"Row shouldn't great than %d and Column shouldn't great than %d!\n",
					row, column);
			printf("Row of Seat :");
			scanf("%d", &newrow);
			printf("Column of Seat :");
			scanf("%d", &newcolumn);
		} while (newrow > row || newcolumn > column);

		p = Seat_Srv_FindByRowCol(list, newrow, newcolumn);
		if (p) {
			printf("State of Seat [%d,%d]: [%c]:", newrow, newcolumn,
					Seat_UI_Status2Char(p->data.status));
			fflush(stdin);
			p->data.status = Seat_UI_Char2Status(getchar());
			printf(
					"-------------------------------------------------------------------\n");
			if (Seat_Srv_Modify(&(p->data))) {
				rtn = 1;
				printf("The seat data updated successfully!\n");
			} else
				printf("The seat data updated failed!\n");
		} else
			printf("The seat is not exist!\n");
		printf(
				"-------------------------------------------------------------------\n");
		printf("[U]pdate more, [R]eturn:");
		fflush(stdin);
		scanf("%c", &choice);
		fflush(stdin);
	} while ('u' == choice || 'U' == choice);
	return rtn;
}

int Seat_UI_Delete(seat_list_t list, int row, int column) {

	int delSeatCount = 0;
	int newrow, newcolumn;
	seat_node_t *p;
	char choice;

	do {
		/*system("cls");*/
		printf(
				"\n==================================================================\n");
		printf(
				"**************************  Delete  Seat  ************************\n");
		printf(
				"------------------------------------------------------------------\n");
		do {
			fflush(stdin);
			printf("Row shouldn't great than %d and Column shouldn't great than %d!\n",
					row, column);
			printf("Row of the Seat:");
			scanf("%d", &(newrow));
			printf("Column of the Seat:");
			scanf("%d", &(newcolumn));
			fflush(stdin);
		} while (newrow > row || newcolumn > column);

		p = Seat_Srv_FindByRowCol(list, newrow, newcolumn);
		if (p) {
			printf(
					"==================================================================\n");
			if (Seat_Srv_DeleteByID(p->data.id)) {
				printf("The seat deleted successfully!\n");

				delSeatCount++;
				List_FreeNode(p);	//�ͷŽ����λ���p
			}
		} else {
			printf("The seat does not exist!\n");
		}

		printf(
				"------------------------------------------------------------------\n");
		printf("[D]elete more, [R]eturn:");
		fflush(stdin);
		scanf("%c", &choice);
		fflush(stdin);
	} while ('d' == choice || 'D' == choice);
	return delSeatCount;
}

