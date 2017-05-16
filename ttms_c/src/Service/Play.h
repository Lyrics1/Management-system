/*
* File name:  Play.h
* File ID:	  TTMS_SSF_Play_Srv_Head
* Author:     Huang Ru
* Version:    V1.0
* Date: 	  2015-04-25
*/

#ifndef PLAY_H_
#define PLAY_H_

#include "../Common/common.h"

typedef enum {
	PLAY_TYPE_FILE=1,
	PLAY_TYPE_OPEAR=2,
	PLAY_TYPE_CONCERT=3
} play_type_t;//��Ŀ���Ͷ��壬1��ʾ��Ӱ��2��ʾ��磬3��ʾ���ֻ�

typedef enum {
	PLAY_RATE_CHILD = 1,
	PLAY_RATE_TEENAGE = 2,
	PLAY_RATE_ADULT = 3
} play_rating_t;//�ݳ��������Ͷ��壬1��ʾ��ͯ�ɹۿ���2��ʾ������ɹۿ���3��ʾ���˿ɹۿ�

typedef struct {//��Ŀ��Ϣ����
	int id;                  //��ϢID��
	char name[31];           //��Ŀ����
	play_type_t type;        //��Ŀ����
	char area[9];            //��Ŀ��Դ����
	play_rating_t rating;    //��Ŀ����
	int duration; //�Է���Ϊ��λ   //�ݳ�ʱ��
	user_date_t start_date;  //��ӳ����
	user_date_t end_date;    //��������
	int price;               //Ʊ�ļ۸�
} play_t;

//�����Ŀ��Ϣ��˫������ṹ
typedef struct play_node {
	play_t data;
	struct play_node *next, *prev;
} play_node_t, *play_list_t;

/*
 * Function:    Play_Srv_Add
 * Function ID:	TTMS_SCU_Play_Srv_Add
 * Description: ���һ����Ŀ��Ϣ
 * Input:       ����ӵľ�Ŀ��Ϣ����
 * Output:      ��
 * Return:      ��ӵļ�¼��
 */
inline int Play_Srv_Add(const play_t *data);

/*
 * Function:    Play_Srv_Modify
 * Function ID:	TTMS_SCU_Play_ Srv _Mod
 * Description: ����һ����Ŀ��Ϣ
 * Input:       �����µľ�Ŀ��Ϣ����
 * Output:      ��
 * Return:      ���µľ�Ŀ��Ϣ����0��ʾδ�ҵ���1��ʾ�ҵ�������
 */
inline int Play_Srv_Modify(const play_t *data);

/*
 * Function:    Play_Srv_DeleteByID
 * Function ID:	TTMS_SCU_Play_Srv_DelByID
 * Description: ����ID��ɾ����Ŀ��Ϣ
 * Input:       ��ɾ���ľ�ĿID��
 * Output:      ��
 * Return:      0��ʾɾ��ʧ�ܣ�1��ʾɾ���ɹ�
 */
inline int Play_Srv_DeleteByID(int ID);

/*
 * Function:    Play_Srv_FetchByID
 * Function ID:	TTMS_SCU_Play_Srv_FetchByID
 * Description: ����ID�Ų���һ����Ŀ��Ϣ������buf��ָ��Ԫ
 * Input:       �����ҵľ�ĿID�ţ������ҵ�����Ϣ���浽buf��
 * Output:      ��
 * Return:      0��ʾδ�ҵ���1��ʾ�ҵ���
 */
inline int Play_Srv_FetchByID(int ID, play_t *buf);

/*
 * Function:    Play_Srv_FetchAll
 * Function ID:	TTMS_SCU_Play_Srv_FetchAll
 * Description: �����о�Ŀ��Ϣ������һ������
 * Input:       list��Ŀ��Ϣ�����ͷָ��
 * Output:      ��
 * Return:      ���ز��ҵ��ļ�¼��Ŀ
 */
inline int Play_Srv_FetchAll(play_list_t list);

/*
 * Function:    Play_Srv_FetchByName
 * Function ID:	TTMS_SCU_Play_Srv_FetchByName
 * Description: ���ݾ�Ŀ���Ʋ��Ҿ�Ŀ����������ľ�Ŀ����
 * Input:       listΪ���ҵ��ľ�Ŀ��Ϣ�����ͷָ�룬condtΪģ����ѯ�Ĺؼ���
 * Output:      ��
 * Return:      ���ز��ҵ��ļ�¼��Ŀ
 */
inline int Play_Srv_FetchByName(play_list_t list, char condt[]);

/*
 * Function:    Play_Srv_FilterByName
 * Function ID:	TTMS_SCU_Play_Srv_FilterByName
 * Description: ���ݾ�Ŀ���Ʋ��Ҿ�Ŀ����������ľ�Ŀ����
 * Input:       listΪ���ҵ��ľ�Ŀ��Ϣ�����ͷָ�룬filterΪ���˴�
 * Output:      ��
 * Return:      ���ز��ҵ��ļ�¼��Ŀ
 */
int Play_Srv_FilterByName(play_list_t list, char filter[]);

#endif //PLAY_H_
