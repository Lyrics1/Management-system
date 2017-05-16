/*
* File name:  Schedule_UI.h
* File ID:	  TTMS_SSF_Schedule_UI_Head
* Author:     Huang Ru
* Version:    V1.0
* Date: 	  2015-04-25
*/


#ifndef SCHEDULE_UI_H_
	#define SCHEDULE_UI_H_
	#include "../Common/list.h"
	#include "../Service/Schedule.h"
	#include "../Service/Play.h"

	static const int SCHEDULE_PAGE_SIZE=5;

	/*
	 * Function:    Schedule_UI_Add
	 * Function ID:	TTMS_SCU_Schedule_UI_Add
	 * Description: ���һ���ݳ��ƻ���Ϣ
	 * Input:       ��ؾ�Ŀ��ID��
	 * Output:      ������Ϣʱ�ĸ�����ʾ
	 * Return:      ��ӵļ�¼��
	 */
	int Schedule_UI_Add(int play_id);

	/*
	 * Function:    Schedule_UI_Modify
	 * Function ID:	TTMS_SCU_Schedule_UI_Mod
	 * Description: ����ID�Ÿ����ݳ��ƻ���Ϣ
	 * Input:       �����µľ�ĿID��
	 * Output:      ������Ϣʱ�ĸ�����ʾ
	 * Return:      ���µľ�Ŀ��Ϣ����0��ʾδ�ҵ���1��ʾ�ҵ�������
	 */
	int Schedule_UI_Modify(int id);

	/*
	 * Function:    Schedule_UI_Delete
	 * Function ID:	TTMS_SCU_Schedule_UI_Del
	 * Description: ����ID��ɾ���ݳ��ƻ���Ϣ
	 * Input:       ��ɾ���ľ�ĿID��
	 * Output:      ��ʾɾ���Ƿ�ɹ�
	 * Return:      0��ʾɾ��ʧ�ܣ�1��ʾɾ���ɹ�
	 */
	int Schedule_UI_Delete(int id);

	/*
	 * Function:    Schedule_UI_Query
	 * Function ID:	TTMS_SCU_Schedule_UI_Qry
	 * Description: ����ID�Ų�ѯ�ݳ��ƻ���Ϣ
	 * Input:       �����ҵľ�ĿID��
	 * Output:      ���ҵ��ľ�Ŀ��Ϣ
	 * Return:      0��ʾδ�ҵ���1��ʾ�ҵ���
	 */
	int Schedule_UI_Query(int id);

	/*
	 * Function:    Schedule_UI_MgtEntry
	 * Function ID:	TTMS_SCU_Schedule_UI_MgtEnt
	 * Description: �ݳ��ƻ�������棬�ݳ��ƻ����վ�Ŀ���й����Ծ�ĿID��Ϊ����
	 * Input:       ��ؾ�ĿID��
	 * Output:      �ݳ��ƻ���Ϣ
	 * Return:      ��
	 */
	void Schedule_UI_MgtEntry(int play_id);

	/*
	 * Function:    Schedule_UI_ListByPlay
	 * Function ID:	TTMS_SCU_Schedule_UI_ListByPlay
	 * Description: ���б�ģʽ��ʾ������Ŀ���ݳ��ƻ���Ϣ
	 * Input:       listΪ���ҵ����ݳ��ƻ���Ϣ�����ͷָ�룬playΪ��Ŀ��Ϣ��pagingΪ��ʾ��ҳ���ò���
	 * Output:      ��
	 * Return:      ���ز��ҵ��ļ�¼��Ŀ
	 */
	void Schedule_UI_ListByPlay(const play_t *play, schedule_list_t list, Pagination_t paging);

	/*
	 * Function:    Schedule_UI_ListAll
	 * Function ID:	TTMS_SCU_Schedule_UI_List
	 * Description: ��ʾ���е��ݳ��ƻ���Ϣ
	 * Input:       ��
	 * Output:      ���о�Ŀ����Ϣ
	 * Return:      ��
	 */
	void Schedule_UI_ListAll(void);

#endif // SCHEDULES_UI_H_
