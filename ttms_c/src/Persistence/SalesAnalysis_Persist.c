/*
 * salesanalysisPersist.c
 *
 *  Created on: 2015��5��8��
 *      Author: Administrator
 */

#include "../Persistence/EntityKey_Persist.h"
//#include "../Service/play.h"
#include "../Service/Sale.h"
#include "../Service/SalesAnalysis.h"
#include "../Common/list.h"
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include <string.h>

//static const char PLAY_DATA_FILE[] = "play.dat";
static const char SALE_DATA_FILE[] = "sale.dat";
static const char SALESANALYSIS_DATA_FILE[] = "salesanalysis.dat";

//��һ��salesanalysis��¼��*data��д��salesanalysis.dat�ļ����ɹ�return 1������return 0
int Salesanalysis_Perst_Insert(const salesanalysis_t *data) {

	return 1;

}

//������salesanalysis.dat�ļ��������۷�����salesanalysis������
int SalesAnalysis_Perst_SelectAll(salesanalysis_list_t list) {

	return 1;

}

//������Sale.dat�ļ�����list��sale_list_t�� ������������list����
int Sale_Perst_SelectAll(sale_list_t list) {

	return 1;

}

