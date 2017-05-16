/*
* File name:  Play_UI.c
* File ID:	  TTMS_SSF_Play_UI_Sour
* Author:     Huang Ru
* Version:    V1.0
* Date: 	  2015-04-25
*/


#include "Play_UI.h"
#include "Schedule_UI.h"

#include "../Common/list.h"
#include "../Service/play.h"
#include "../Service/EntityKey.h"

#include <stdio.h>
#include <stdlib.h>


	/*
	 * Function:    Play_UI_ShowList
	 * Function ID:	TTMS_SCU_Play_UI_Show
	 * Description: ���б�ģʽ��ʾ��Ŀ��Ϣ
	 * Input:       list��Ŀ��Ϣ�����ͷָ�룬paging��ҳ���ò���
	 * Output:      ���о�Ŀ����Ϣ
	 * Return:      ���ز��ҵ��ļ�¼��Ŀ
	 */
void Play_UI_ShowList(play_list_t list, Pagination_t paging) {

}

/*
 * Function:    Play_UI_MgtEntry
 * Function ID:	TTMS_SCU_Play_UI_MgtEnt
 * Description: ��Ŀ��Ϣ�������
 * Input:       flagΪ0���������ҳ�棬flagΪ1�����ѯ����
 * Output:      ��Ŀ�������
 * Return:      ��
 */
void Play_UI_MgtEntry(int flag){
   play_list_t     list;
    play_node_t     *p;
    Pagination_t    paging;
    char            choice;
    int             i,id;
    List_Init(list,play_node_t);
    
    List_Init(list, play_node_t);
	paging.offset = 0;
	paging.pageSize = PLAY_PAGE_SIZE;

    
    paging.totalRecords = Play_Srv_FetchAll(list);
    Paging_Locate_FirstPage(list,paging);         
    system("cls");
    do {
        printf("--------------------------------- ��Ŀ�б� ---------------------------------------\n");
        printf("ID\t��Ŀ����\t��Ŀ����\t��Ŀ��Ʒ����\t��Ŀ�ȼ�\t��ӳʱ��\t��ʼ��ӳʱ��\t������ӳʱ��\tƱ��\n\n");
        printf("---------------------------------------------------------------------------------------\n");
       	for (i = 0, p = (play_node_t *) (paging.curPos);
				p != list && i < paging.pageSize; i++) {
            printf("%d\t%s\t%s\t%s\t%s\t%d\t\t%d.%d.%d\t%d.%d.%d\t%d\n", p->data.id, p->data.name,
                   (p->data.type == 1)?"��Ӱ":(p->data.type == 2?"���":"���ֻ�"),
                   p->data.area,
                   (p->data.rating == 1)?"��ͯ":(p->data.rating == 2?"������":"������"),
                   p->data.duration,
                   p->data.start_date.year,p->data.start_date.month,p->data.start_date.day,
                   p->data.end_date.year,p->data.end_date.month,p->data.end_date.day,
                   p->data.price);
            p=p->next;
        }
        printf("--��ҳ��:%2d ------------------------------ ҳ�� %2d/%2d --\n",
               paging.totalRecords, Pageing_CurPage(paging),
               Pageing_TotalPages(paging));
        if(flag == 1)
        {				printf("------------------------��Ŀ��Ϣ��ѯҳ��-----------------------\n\n");
						printf("[s]��ѯ�ݳ��ƻ�\n\n");
						printf("[Q]��ѯ��Ŀ��Ϣ\n\n");
						printf("[R]�˳�����ϵͳ\n\n");
            do{
                printf("please input your choice :");
		fflush(stdin);
                scanf("%c", &choice);
                switch(choice)
                {
                		   case 's':
                           case 'S':
                               printf("������ ID:");
                               scanf("%d",&id);
                               if (Play_UI_Query(id)) {
                                   Schedule_UI_MgtEntry(id);
                               }
                               else
                                   printf("�˾�Ŀ������\n");
                               break;
                           case 'q':
                           case 'Q':
                                   printf("P������ ID:");
                                   scanf("%d",&id);
                                   if (Play_UI_Query(id)) {
                                     paging.totalRecords = Play_Srv_FetchAll(list);
                                     Paging_Locate_LastPage(list, paging, play_node_t);
                                     }
                                    break;

                }
            }while(choice != 'a' && choice != 'A' && choice != 'd' && choice != 'D' &&
                   choice != 'u' && choice != 'U' && choice != 'r' && choice != 'R'&& choice != 'S' && choice != 's');
        }
        else if(flag == 0)
        {


            printf("---------------------------------��Ŀ��Ϣ����ҳ��--------------------------------\n");
            					printf("[s]�ݳ��ƻ�\n\n");
                    			//printf("[Q]��ѯ��Ŀ��Ϣ\n\n");
                    			printf("[A]������Ŀ��Ϣ\n\n");
                    			printf("[D]ɾ����Ŀ��Ϣ\n\n");
                    			printf("[U]�޸ľ�Ŀ��Ϣ\n\n");
                    			printf("[P]��ǰҳ��\n\n");
                    			printf("[n]��ǰҳ�����ҳ��\n\n");
                    			printf("[R]�˳�����ϵͳ\n\n");
            do{
                printf("please input your choice : ");
		fflush(stdin);
                scanf("%c", &choice);
            }while(choice != 'q' && choice != 'Q' && choice != 'S' && choice != 's' &&
                   choice != 'r' && choice != 'R');
        }
        
        switch (choice) {
            case 's':
            case 'S':
                printf("������ ID:");
                scanf("%d",&id);
                if (Play_UI_Query(id)) {
                    Schedule_UI_MgtEntry(id);
                }
                else
                    printf("�˾�Ŀ������\n");
                break;
            case 'q':
            case 'Q':
                printf("P������ ID:");
                scanf("%d",&id);
                 if (Play_UI_Query(id)) {
                    paging.totalRecords = Play_Srv_FetchAll(list);
                    Paging_Locate_LastPage(list, paging, play_node_t);    
                }
                break;
            case 'a':
            case 'A':
                if (Play_UI_Add()) {
                    paging.totalRecords = Play_Srv_FetchAll(list);
                    Paging_Locate_LastPage(list, paging, play_node_t);    
                }
                break;
            case 'd':
            case 'D':
                printf("������ ID:");
                scanf("%d",&id);
                if (Play_UI_Delete(id)) {
                    paging.totalRecords = Play_Srv_FetchAll(list);
                    List_Paging(list, paging, play_node_t);
                }
                break;
            case 'u':
            case 'U':
                printf("������ ID:");
                scanf("%d",&id);
                if (Play_UI_Modify(id)) {
                    paging.totalRecords = Play_Srv_FetchAll(list);
                    List_Paging(list, paging, play_node_t);
                }
                break;
            case 'p':
            case 'P':
                if (1 < Pageing_CurPage(paging)) {
                    Paging_Locate_OffsetPage(list, paging, -1, play_node_t); 
                }
                break;
            case 'n':
            case 'N':
                if (Pageing_TotalPages(paging) > Pageing_CurPage(paging)) {
                    Paging_Locate_OffsetPage(list, paging, 1, play_node_t);  
                }
                break;
        }
        
    } while (choice != 'r' && choice != 'R');
    
    List_Destroy(list,play_node_t);
}


/*
 * Function:    Play_UI_Add
 * Function ID:	TTMS_SCU_Play_UI_Add
 * Description: ���һ����Ŀ��Ϣ
 * Input:       ��
 * Output:      ������Ϣʱ�ĸ�����ʾ
 * Return:      ��ӵļ�¼��
 */
int Play_UI_Add(void)
{
 int     newCount = 0;
    int     key;
    char    choice;
    int a;
    play_t  pnew;
    system("cls");
    printf("--------------------------����ӡ� ��Ŀ --------------------------\n");
    do{
        printf("�������Ŀ����:\n");
        scanf("%s",pnew.name);
        fflush(stdin);
        printf("����������ѡ��: 1.��Ӱ  2.���  3.���ֻ�\n");
        scanf("%d",&a);
               			fflush(stdin);
               			switch(a)
               			{
               				case 1:pnew.type=(play_type_t)a;break;
               				case 2:pnew.type=(play_type_t)a;break;
               				case 3:pnew.type=(play_type_t)a;break;
               				default:printf("error\n");break;
               			}
        printf("���������:\n");
        scanf("%s",pnew.area);
        printf("�������ʺϷ�Χ: 1.��ͯ  2.������  3.������\n");
        scanf("%d",&a);
                			switch(a)
                			{
                				case 1:pnew.rating=( play_rating_t)a;break;
                				case 2:pnew.rating=( play_rating_t)a;break;
                				case 3:pnew.rating=( play_rating_t)a;break;
                				default:printf("error\n");
                			}

        printf("������ʱ�� (minute):");
        scanf("%d",&pnew.duration);
        printf("�����뿪ʼ����(like 2015 06 29):\n");
        scanf("%d %d %d",&pnew.start_date.year, &pnew.start_date.month, &pnew.start_date.day);
        printf("������������� (like 2015 06 29):\n");
        scanf("%d %d %d",&pnew.end_date.year, &pnew.end_date.month, &pnew.end_date.day);
        printf("������۸�:");
        scanf("%d",&pnew.price);
        key = EntKey_Srv_CompNewKey("play"); 
        pnew.id = key;
        if (Play_Srv_Add(&pnew)) {
            printf("��ӳɹ�!!\n");
            newCount++;
            printf("�Ƿ����? (y/n):");
            getchar();
		fflush(stdin);
            scanf("%c",&choice);
        }
        else
        {
            printf("���ʧ��!\n");
            //sleep(1);
            return newCount;
        }
    }while (choice == 'y' || choice == 'Y');
    return newCount;
}

/*
 * Function:    Play_UI_Modify
 * Function ID:	TTMS_SCU_Play_UI_Mod
 * Description: ���¾�Ŀ��Ϣ
 * Input:       �����µľ�ĿID��
 * Output:      ������Ϣʱ�ĸ�����ʾ
 * Return:      ���µľ�Ŀ��Ϣ����0��ʾδ�ҵ���1��ʾ�ҵ�������
 */
int Play_UI_Modify(int id){
	
   int     rtn = 0;
    play_t  temp;
    int a;
    printf("========================= ��Ŀ�޸� =========================\n");
    if (Play_Srv_FetchByID(id,&temp)) {
        printf("--------------------------- ������ ----------------------------\n");
        printf("==================================== ��Ŀ�б� ========================================\n");
               printf("ID\t����\t����\t����\t�ȼ�\tʱ��\t��ʼ����\t��������\t�۸�\n");
               printf("---------------------------------------------------------------------------------------\n");
                   printf("%d\t%s\t%s\t%s\t%s\t%d\t\t%d.%d.%d\t%d.%d.%d\t%d\n", temp.id, temp.name,
                          (temp.type == 1)?"��Ӱ":(temp.type == 2?"���":"���ֻ�"),
                        		  temp.area,
                          (temp.rating == 1)?"��ͯ":(temp.rating == 2?"������":"������"),
                        		  temp.duration,
								  temp.start_date.year,temp.start_date.month,temp.start_date.day,
								  temp.end_date.year,temp.end_date.month,temp.end_date.day,
								  temp.price);

        printf("--------------------------- New Data ----------------------------\n");
        printf("�������µľ�Ŀ����:\n");

        scanf("%s",temp.name);
        	fflush(stdin);
        printf("�������µ����� ��     1.��Ӱ  2.���  3.���ֻ�\n");
          scanf("%d",&a);
        			fflush(stdin);
        			switch(a)
        			{
        				case 1:temp.type=(play_type_t)a;break;
        				case 2:temp.type=(play_type_t)a;break;
        				case 3:temp.type=(play_type_t)a;break;
        				default:printf("error\n");break;
        			}
        printf("�������µĵ���:\n");
        scanf("%s",temp.area);
        printf("(1.��ͯ  2.������  3.������)\n");
        printf("�������µ��ʺϷ�Χ:\n");
        scanf("%d",&a);
        			switch(a)
        			{
        				case 1:temp.rating=( play_rating_t)a;break;
        				case 2:temp.rating=( play_rating_t)a;break;
        				case 3:temp.rating=( play_rating_t)a;break;
        				default:printf("error\n");
        			}
        printf("�������µ�ʱ�� (minute):");
        scanf("%d",&temp.duration);
        printf("�����뿪ʼ���� ( ���磺2016 06 18):\n");
        scanf("%d %d %d",&temp.start_date.year, &temp.start_date.month, &temp.start_date.day);
        printf("������������� ( ���磺2016 06 25):\n");
        scanf("%d %d %d",&temp.end_date.year, &temp.end_date.month, &temp.end_date.day);
        printf("�������µ�Ʊ��:");
        scanf("%d",&temp.price);
        if (Play_Srv_Modify(&temp)) {
            rtn = 1;
            printf("�޸ĳɹ�  [Successful] \n");
        }
        else
            printf("�޸�ʧ��   [File]\n");
    }
    else
    {
        printf("�þ�Ŀ������!\n");
        fflush(stdin);
        return 0;
    }
    return rtn;


}

/*
 * Function:    Play_UI_Delete
 * Function ID:	TTMS_SCU_Play_UI_Del
 * Description: ����ID��ɾ����Ŀ��Ϣ
 * Input:       ��ɾ���ľ�ĿID��
 * Output:      ��ʾɾ���Ƿ�ɹ�
 * Return:      0��ʾɾ��ʧ�ܣ�1��ʾɾ���ɹ�
 */
int Play_UI_Delete(int id){
int rtn = 0;
    printf(" --------------------ɾ����Ŀ------------------------\n");
    if (Play_Srv_DeleteByID(id)) {
        rtn = 1;
        printf("ɾ���ɹ�!\n");
    }
    else
        printf("ɾ��ʧ��!\n");
    return rtn;

	
}

/*
 * Function:    Play_UI_Query
 * Function ID:	TTMS_SCU_Play_UI_Qry
 * Description: ����ID�Ų�ѯ��Ŀ��Ϣ
 * Input:       �����ҵľ�ĿID��
 * Output:      ���ҵ��ľ�Ŀ��Ϣ
 * Return:      0��ʾδ�ҵ���1��ʾ�ҵ���
 */
int Play_UI_Query(int id){
	
   
    int     rtn = 0;
    play_t  buf;
    if (Play_Srv_FetchByID(id,&buf)) {
        rtn = 1;
        printf("Found this play!!\n");
        printf("=========================== ��Ŀ��Ϣ===========================\n");
        printf("ID\t����\t����\t����\t�ʺϷ�Χ\tʱ��\t��ʼ����\t��������\tƱ��\n");
        printf("-----------------------------------------------------------------------\n");
        printf("%d\t%s\t%s\t%s\t%s\t%d\t%d.%d.%d\t%d.%d.%d\t%d\n", buf.id, buf.name,
               (buf.type == 1)?"��Ӱ":(buf.type == 2?"���":"���ֻ�"),
               buf.area,
               (buf.rating == 1)?"��ͯ":(buf.rating == 2?"������":"������"),
               buf.duration,
               buf.start_date.year, buf.start_date.month, buf.start_date.day,
               buf.end_date.year, buf.end_date.month, buf.end_date.day,
               buf.price);
        	   fflush(stdin);
    }
    else
        printf("�˾�Ŀ������.\n");

    return rtn;


}
