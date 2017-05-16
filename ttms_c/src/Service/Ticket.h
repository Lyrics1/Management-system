#ifndef TICKET_H_
#define TICKET_H_

typedef enum{
	TICKET_AVL=0,		//����
	TICKET_SOLD=1,		//����
	TICKET_RESV=9		//Ԥ��
}ticket_status_t;

typedef struct {
	int id;
	int schedule_id;
	int seat_id;
	int price;
	ticket_status_t status;
} ticket_t;

//˫������
typedef struct ticket_node {
	ticket_t data;
	struct ticket_node *next, *prev;
} ticket_node_t, *ticket_list_t;

int Ticket_Srv_AddBatch(int schedule_id, int studio_id);

int Ticket_Srv_DeleteBatch(int schedule_id);

int Ticket_Srv_Modify(const ticket_t * data);

int Ticket_Srv_FetchByID(int ID, ticket_t *buf) ;

//���ݼƻ�ID��ȡ�����ݳ�Ʊ
int Ticket_Srv_FetchBySchID(ticket_list_t list, int schedule_id);

//�����ݳ��ƻ�ID��ͳ�������ʼ�Ʊ��������Ʊ��������
int Ticket_Srv_StatRevBySchID(int schedule_id, int *soldCount, int *totalCount);

//������λID��list���Ҷ�ӦƱ
inline ticket_node_t * Ticket_Srv_FindBySeatID(ticket_list_t list, int seat_id);


#endif //TICKET_H_
