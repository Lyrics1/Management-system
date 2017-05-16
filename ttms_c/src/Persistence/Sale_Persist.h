#ifndef SLAEINGPERSIST_H
#define SALEINGPERSIST_H

#include "../Service/Sale.h"
#include "../Common/common.h"

int Sale_Perst_Insert(const sale_t *data);
int Sale_Perst_DeleteByID(int saleID);


//�����û�ID�������ʱ�������ڵ����ۼ�¼
int Sale_Perst_SelectByUsrID(sale_list_t list, int usrID,
		user_date_t stDate, user_date_t endDate);

//�����û�ID�������ʱ�������ڵ����ۼ�¼
int Sale_Perst_SelectByDate(sale_list_t list,
		user_date_t stDate, user_date_t endDate);

/*
int Sale_Infor_By_Time(char queryTime[][30], sale_list_t head);

//����Ʊ��id���Ʊ�Ļ�����Ϣ �������������ticket������ã����ǿ������Ǹ��ļ����ֲ�һ������������д��һ�ݣ����Ǳ߿���ֱ�ӵ���api�����������Ҫ�ǻ��ƱƱ�ļ۸�,
int Select_Price_By_Playid(int id, ticket_t *buf);
*/
#endif
