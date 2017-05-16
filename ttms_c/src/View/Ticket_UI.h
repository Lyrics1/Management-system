#ifndef TICKET_UI_H_
#define TICKET_UI_H_
#include "../Common/list.h"
#include "../Service/Ticket.h"
#include "../Service/Seat.h"
#include "../Service/Schedule.h"

static const int TICKET_PAGE_SIZE=8;

int UpdateTicket(int id);

int QueryTicket(int id);

void ListTickets(void);

//״̬ת��Ϊ�ַ�
inline char Ticket_UI_Status2Char(ticket_status_t status);

//����ƱID��ӡƱ
void Ticket_UI_Print(int ID);

//���ݼƻ�ID��ʾ����Ʊ
void Ticket_UI_ListBySch(const schedule_t *sch,	ticket_list_t tickList, seat_list_t seatList);

#endif /* TICKET_UI_H_ */
