#ifndef TICKETPERSIST_H_
#define TICKETPERSIST_H_

#include "../Service/Ticket.h"
#include "../Service/EntityKey.h"


int Ticket_Perst_Insert(ticket_list_t list);//�����ݳ��ƻ�ʱ����������Ʊ

int Ticket_Perst_Delete(int schedule_id);

int Ticket_Perst_Update(const ticket_t * data);

int Ticket_Perst_SelectByID(int ID, ticket_t *buf) ;

int Ticket_Perst_SelectAll(ticket_list_t list) ;

int Ticket_Perst_SelectBySchID(ticket_list_t list, int schedule_id);

//����Ʊ�����б�����Ʊ
int Ticket_Perst_SelectByKeyList(ticket_list_t list, entkey_list_t keyList);


#endif /* TICKETPERSIST_H_ */
