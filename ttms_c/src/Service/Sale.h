#ifndef SALE_H_
#define SALE_H_
#include "../Common/common.h"
#include "Account.h"
#include "Ticket.h"

//�������ͣ�Ϊ��Ʊ����Ʊ
typedef enum{
	SALE_SELL=1,	//��Ʊ
	SALE_RETURN=-1	//��Ʊ
}sale_type_t;

// ����ͳ�Ƶ���Ϣ �� ��ʾ��Ʊ����ġ� ����ĳ��ʱ������۳�����Ʊ �� ����ĳ��ticket_id , ���Ҫ�嵽�ҵ�ģ����ȥ �� ����Ʊ��ʱ����м�¼ ��
typedef struct {
	long id;      		//���ۼ�¼ID
	int user_id;		//��ƱԱID
	int ticket_id;		//ƱID
	user_date_t date ;	//��������
	user_time_t time;	//����ʱ��
	int value;			//Ʊ��
	sale_type_t type; //��������
}sale_t;

//˫������
typedef struct sale_node {
	sale_t data;
	struct sale_node *next, *prev;
} sale_node_t, *sale_list_t;

//����Ʊ����Ϣ���뵽��������Ϣ
int Sale_Srv_Add(const sale_t *data);

//ɾ����������Ϣ
int Sale_Srv_DeleteByID(int saleID);

int Sale_Srv_selectByTime(char querytime[][30], sale_list_t head);

int Sale_Srv_SelectByUid(char *queryuid, sale_list_t head);

#endif //SALE_H_

