/*
 * salesanalysis.c
 *
 *  Created on: 2015��6��12��
 *      Author: Administrator
 */
#include <string.h>
#include <stdio.h>
#include "SalesAnalysis.h"
#include "../Persistence/Sale_Persist.h"
#include "../Persistence/SalesAnalysis_Persist.h"
#include "Sale.h"
#include "Ticket.h"
#include "Play.h"
#include "Schedule.h"
#include "../Common/list.h"

//����Ա��usrID�ڸ���ʱ����������۶�������۶�
inline int Salesanalysis_Srv_CompSaleVal(int usrID, user_date_t stDate, user_date_t endDate){

	return 1;
}

//��Ͼ�ĿPlay.dat,ͳ���������ݣ��������۷���salesanalysis_list_t list��������list������
int Salesanalysis_Srv_StaticSale(salesanalysis_list_t list) {

	return 1;
}

//�Ծ�Ժ��ӰƱ������
void SalesAnalysis_Srv_SortBySale(salesanalysis_list_t list){

}


