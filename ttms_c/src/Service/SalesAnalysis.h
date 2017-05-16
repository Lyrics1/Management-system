/*
 *salesanalysis.h
 *
 *  Created on: 2015��5��8��
 *      Author: Administrator
 */
#ifndef SALESANALYSIS_H_
#define SALESANALYSIS_H_

#include "../Common/common.h"
#include "../Service/Play.h"
#include "../Service/Sale.h"

typedef struct {
	int play_id; 	//��Ŀ���
	char name[31]; 	//��Ŀ����
	char area[9]; 	//��Ŀ����
	int duration; 	//�Է���Ϊ��λ
	long totaltickets; //��ĿƱ��������
	long sales; 	//��ӰƱ��
	int price; 		//��ĿƱ��
	user_date_t start_date; 	//��Ŀ��ӳ����
	user_date_t end_date; 	//��Ŀ��ӳ����
} salesanalysis_t;

//˫������
typedef struct salesanalysis_node {
	salesanalysis_t data;
	struct salesanalysis_node *next, *prev;
} salesanalysis_node_t, *salesanalysis_list_t;



//����Ա��usrID�ڸ���ʱ����������۶�
inline int Salesanalysis_Srv_CompSaleVal(int usrID, user_date_t stDate, user_date_t endDate);

//ͳ����������
int Salesanalysis_Srv_StaticSale(salesanalysis_list_t list);

//����Ʊ������
void SalesAnalysis_Srv_SortBySale(salesanalysis_list_t list);


#endif //SALESANALYSIS_H_
