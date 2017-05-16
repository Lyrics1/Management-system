/*
 *  Seat.c
 *
 *  Created on: 2015��6��12��
 *  Author: lc
 */
#include <stdlib.h>
#include "../Common/list.h"
#include "Seat.h"
#include "../Persistence/Seat_Persist.h"
#include "EntityKey.h"

inline int Seat_Srv_Add(const seat_t *data){
	return Seat_Perst_Insert(data);
}

inline int Seat_Srv_AddBatch(seat_list_t list){
	return Seat_Perst_InsertBatch(list);
}

inline int Seat_Srv_Modify(const seat_t *data){
	return Seat_Perst_Update(data);
}

inline int Seat_Srv_DeleteByID(int ID){
	return Seat_Perst_DeleteByID(ID);
}

inline int Seat_Srv_FetchByID(int ID, seat_t *buf){
	return Seat_Perst_SelectByID(ID, buf);
}

inline int Seat_Srv_DeleteAllByRoomID(int roomID){
	return Seat_Perst_DeleteAllByRoomID(roomID);
}

//�����ݳ���ID������λ
int Seat_Srv_FetchByRoomID(seat_list_t list, int roomID){

	//�����ݳ���ID������λ
	int SeatCount=Seat_Perst_SelectByRoomID(list, roomID);

	//����λ��������
	Seat_Srv_SortSeatList(list);
	return SeatCount;
}

/*���ݷ�ӳ��ID��ȡ��Ч����λ*/
int Seat_Srv_FetchValidByRoomID(seat_list_t list, int roomID)
{
	//�����ݳ���ID������λ
	int SeatCount=Seat_Perst_SelectByRoomID(list, roomID);

	seat_node_t *p;
	//ȥ����Ч��λ
	List_ForEach(list, p){
		if(p->data.status!=SEAT_GOOD){
			List_FreeNode(p);
			SeatCount--;
		}
	}

	//����λ��������
	Seat_Srv_SortSeatList(list);
	return SeatCount;
}


//�����С�������ʼ���ݳ�������λ
int Seat_Srv_RoomInit(seat_list_t list, int roomID, int rowsCount,
		int colsCount) {
	int i, j;

	seat_node_t *p;

	//������ȡ����
	long seatID=EntKey_Srv_CompNewKeys("Seat", rowsCount*colsCount);

	//�Ȱ�����������Ĭ����λ���γ�����
	for (i = 1; i <= rowsCount; i++){
		for (j = 1; j <= colsCount; j++) {
			p = (seat_node_t *) malloc(sizeof(seat_node_t));
			p->data.id = seatID;
			p->data.roomID = roomID;
			p->data.row = i;
			p->data.column = j;
			p->data.status = SEAT_GOOD;
			seatID++;
			List_AddTail(list, p);
		}
	}

	return Seat_Perst_InsertBatch(list);
}

//����λ����list���а���λ�кź��к�����
void Seat_Srv_SortSeatList(seat_list_t list) {
	seat_node_t *p, *listLeft;
	assert(list!=NULL);

	if(List_IsEmpty(list))
		return ;

	//��nextָ�빹�ɵ�ѭ����������һ�����Ͽ�
	list->prev->next = NULL;

	//listLeftָ���һ�����ݽڵ�
	listLeft = list->next;

	//��list������Ϊ��
	list->next = list->prev = list;

	while (listLeft != NULL ) {
		//ȡ����һ�����
		p = listLeft;
		listLeft = listLeft->next;

		//�����p���뵽����������list��
		Seat_Srv_AddToSoftedList(list, p);
	}
}

//�����node���뵽����������list��
void Seat_Srv_AddToSoftedList(seat_list_t list, seat_node_t *node) {

	seat_node_t *p;

	assert(list!=NULL && node!=NULL);

	if(List_IsEmpty(list))	{
		List_AddTail(list, node);
	}else{
		//Ѱ�Ҳ���λ��
		p=list->next;

		while(p!=list && (p->data.row<node->data.row ||
				(p->data.row==node->data.row && p->data.column<node->data.column))){
			p=p->next;
		}

		//�����node���뵽p֮ǰ
		List_InsertBefore(p, node);
	}
}

inline seat_node_t * Seat_Srv_FindByRowCol(seat_list_t list, int row, int column) {

	assert(NULL!=list);
	seat_node_t *p;

	List_ForEach(list,p)
		if (p->data.row == row && p->data.column == column)
			return p;
	return NULL ;
}


inline seat_node_t * Seat_Srv_FindByID(seat_list_t list, int rowID) {

	assert(NULL!=list);
	seat_node_t *p;

	List_ForEach(list,p)
		if (p->data.id==rowID)
			return p;

	return NULL ;
}
