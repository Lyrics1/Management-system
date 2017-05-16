/*
* File name:  Play.c
* File ID:	  TTMS_SSF_Play_Srv_Sour
* Author:     Huang Ru
* Version:    V1.0
* Date: 	  2015-04-25
*/

#include "Play.h"
#include "../Common/list.h"
#include "../Persistence/Play_Persist.h"
#include <string.h>

/*
 * Function:    Play_Srv_Add
 * Function ID:	TTMS_SCU_Play_Srv_Add
 * Description: ���һ����Ŀ��Ϣ
 * Input:       ����ӵľ�Ŀ��Ϣ����
 * Output:      ��
 * Return:      ��ӵļ�¼��
 */
inline int Play_Srv_Add(const play_t *data) {
	return Play_Perst_Insert(data);

}

/*
 * Function:    Play_Srv_Modify
 * Function ID:	TTMS_SCU_Play_ Srv _Mod
 * Description: ����һ����Ŀ��Ϣ
 * Input:       �����µľ�Ŀ��Ϣ����
 * Output:      ��
 * Return:      ���µľ�Ŀ��Ϣ����0��ʾδ�ҵ���1��ʾ�ҵ�������
 */
inline int Play_Srv_Modify(const play_t *data) {
	return Play_Perst_Update(data);
	return 0;
}

/*
 * Function:    Play_Srv_DeleteByID
 * Function ID:	TTMS_SCU_Play_Srv_DelByID
 * Description: ����ID��ɾ����Ŀ��Ϣ
 * Input:       ��ɾ���ľ�ĿID��
 * Output:      ��
 * Return:      0��ʾɾ��ʧ�ܣ�1��ʾɾ���ɹ�
 */
inline int Play_Srv_DeleteByID(int ID) {
	return Play_Perst_DeleteByID(ID);
	return 0;
}

/*
 * Function:    Play_Srv_FetchByID
 * Function ID:	TTMS_SCU_Play_Srv_FetchByID
 * Description: ����ID�Ų���һ����Ŀ��Ϣ������buf��ָ��Ԫ
 * Input:       �����ҵľ�ĿID�ţ������ҵ�����Ϣ���浽buf��
 * Output:      ��
 * Return:      0��ʾδ�ҵ���1��ʾ�ҵ���
 */
inline int Play_Srv_FetchByID(int ID, play_t *buf) {
	return Play_Perst_SelectByID(ID,buf);
	return 0;

}

/*
 * Function:    Play_Srv_FetchAll
 * Function ID:	TTMS_SCU_Play_Srv_FetchAll
 * Description: �����о�Ŀ��Ϣ������һ������
 * Input:       list��Ŀ��Ϣ�����ͷָ��
 * Output:      ��
 * Return:      ���ز��ҵ��ļ�¼��Ŀ
 */
inline int Play_Srv_FetchAll(play_list_t list) {
	return Play_Perst_SelectAll(list);
	return 0;
}

/*
 * Function:    Play_Srv_FetchByName
 * Function ID:	TTMS_SCU_Play_Srv_FetchByName
 * Description: ���ݾ�Ŀ���Ʋ��Ҿ�Ŀ����������ľ�Ŀ����
 * Input:       listΪ���ҵ��ľ�Ŀ��Ϣ�����ͷָ�룬condtΪģ����ѯ�Ĺؼ���
 * Output:      ��
 * Return:      ���ز��ҵ��ļ�¼��Ŀ
 */
int Play_Srv_FetchByName(play_list_t list, char condt[]){
	return Play_Perst_SelectByName(list,condt);
	return 0;
}



/*
 * Function:    Play_Srv_FilterByName
 * Function ID:	TTMS_SCU_Play_Srv_FilterByName
 * Description: ���ݾ�Ŀ���Ʋ��Ҿ�Ŀ����������ľ�Ŀ����
 * Input:       listΪ���ҵ��ľ�Ŀ��Ϣ�����ͷָ�룬filterΪ���˴�
 * Output:      ��
 * Return:      ���ز��ҵ��ļ�¼��Ŀ
 */
int Play_Srv_FilterByName(play_list_t list, char filter[]){
	return 0;
}


