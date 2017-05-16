/*
* File name:  Play_UI.h
* File ID:	  TTMS_SSF_Play_UI_Head
* Author:     Huang Ru
* Version:    V1.0
* Date: 	  2015-04-25
*/


#ifndef PLAY_UI_H_
	#define PLAY_UI_H_
	#include "../Common/list.h"
	#include "../Service/Play.h"

	static const int PLAY_PAGE_SIZE=6;

	/*
	 * Function:    Play_UI_Add
	 * Function ID:	TTMS_SCU_Play_UI_Add
	 * Description: ���һ����Ŀ��Ϣ
	 * Input:       ��
	 * Output:      ������Ϣʱ�ĸ�����ʾ
	 * Return:      ��ӵļ�¼��
	 */
	int Play_UI_Add(void);

	/*
	 * Function:    Play_UI_Modify
	 * Function ID:	TTMS_SCU_Play_UI_Mod
	 * Description: ���¾�Ŀ��Ϣ
	 * Input:       �����µľ�ĿID��
	 * Output:      ������Ϣʱ�ĸ�����ʾ
	 * Return:      ���µľ�Ŀ��Ϣ����0��ʾδ�ҵ���1��ʾ�ҵ�������
	 */
	int Play_UI_Modify(int id);

	/*
	 * Function:    Play_UI_Delete
	 * Function ID:	TTMS_SCU_Play_UI_Del
	 * Description: ����ID��ɾ����Ŀ��Ϣ
	 * Input:       ��ɾ���ľ�ĿID��
	 * Output:      ��ʾɾ���Ƿ�ɹ�
	 * Return:      0��ʾɾ��ʧ�ܣ�1��ʾɾ���ɹ�
	 */
	int Play_UI_Delete(int id);

	/*
	 * Function:    Play_UI_Query
	 * Function ID:	TTMS_SCU_Play_UI_Qry
	 * Description: ����ID�Ų�ѯ��Ŀ��Ϣ
	 * Input:       �����ҵľ�ĿID��
	 * Output:      ���ҵ��ľ�Ŀ��Ϣ
	 * Return:      0��ʾδ�ҵ���1��ʾ�ҵ���
	 */
	int Play_UI_Query(int id);

	/*
	 * Function:    Play_UI_ShowList
	 * Function ID:	TTMS_SCU_Play_UI_Show
	 * Description: ���б�ģʽ��ʾ��Ŀ��Ϣ
	 * Input:       list��Ŀ��Ϣ�����ͷָ�룬paging��ҳ���ò���
	 * Output:      ���о�Ŀ����Ϣ
	 * Return:      ���ز��ҵ��ļ�¼��Ŀ
	 */
	void Play_UI_ShowList(play_list_t list, Pagination_t paging);

	/*
	 * Function:    Play_UI_MgtEntry
	 * Function ID:	TTMS_SCU_Play_UI_MgtEnt
	 * Description: ��Ŀ��Ϣ�������
	 * Input:       flagΪ0���������ҳ�棬flagΪ1�����ѯ����
	 * Output:      ��Ŀ�������
	 * Return:      ��
	 */
	void Play_UI_MgtEntry(int flag);

#endif /* PLAY_UI_H_ */
