/*
* File name:  Play_Persist.c
* File ID:	  TTMS_SSF_Play_Perst_Sour
* Author:     Huang Ru
* Version:    V1.0
* Date: 	  2015-04-25
*/


#include "Play_Persist.h"
#include "../Service/Play.h"
#include "../Common/list.h"
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <assert.h>
#include <string.h>

static const char PLAY_DATA_FILE[] = "Play.dat";//�����Ŀ��Ϣ
static const char PLAY_DATA_TEMP_FILE[] = "PlayTmp.dat";//ɾ�������ʱ����ʱ�����Ŀ��Ϣ

/*
 * Function:    Play_Perst_Insert
 * Function ID:	TTMS_SCU_Play_Perst_Insert
 * Description: �ھ�Ŀ��Ϣ�ļ�ĩβд��һ����Ŀ��Ϣ
 * Input:       �������ļ��ľ�Ŀ��Ϣ����
 * Output:      ���ļ�ʧ�ܵ���Ϣ
 * Return:      д���ļ��ļ�¼��
 */
int Play_Perst_Insert(const play_t *data) {
	assert(NULL!=data);
	FILE *fp;
	fp=fopen(PLAY_DATA_FILE,"ab");
	int rtn=0;
	if(fp == NULL){
		printf("Cannot open file Play.dat!\n");
		return 0;
	}

	rtn=fwrite(data,sizeof(play_t),1,fp);
	fclose(fp);
	return rtn;
}

/*
 * Function:    Play_Perst_Update
 * Function ID:	TTMS_SCU_Play_Perst_Mod
 * Description: ���վ�ĿID�Ÿ����ļ��еľ�Ŀ��Ϣ
 * Input:       �����ļ��и��µľ�Ŀ��Ϣ����
 * Output:      ���ļ�ʧ�ܵ���Ϣ
 * Return:      ���µľ�Ŀ��Ϣ����0��ʾδ�ҵ���1��ʾ�ҵ�������
 */
int Play_Perst_Update(const play_t *data) {
	int Found=0;
	FILE *fp;
	play_t buf;
	fp=fopen(PLAY_DATA_FILE,"rb+");
	if(fp==NULL){
	printf("Cannot open file Play.dat!\n");

	return 0;
	}

		while(!feof(fp))
		{
			if(fread(&buf,sizeof(play_t),1,fp))
			{
				if(buf.id == data->id)
				{
					fseek(fp,-sizeof(play_t),SEEK_CUR);//�ļ���д�س���ǰһ����¼
					fwrite(data,sizeof(play_t),1,fp);
					Found = 1;
					break;
				}
			}
		}
	fclose(fp);
	return Found;
}


/*
 * Function:    Play_Perst_DeleteByID
 * Function ID:	TTMS_SCU_Play_Perst_DelByID
 * Description: ���վ�ĿID��ɾ����Ŀ����Ϣ
 * Input:       ��ɾ���ľ�ĿID��
 * Output:      ���ļ�ʧ�ܵ���Ϣ
 * Return:      0��ʾɾ��ʧ�ܣ�1��ʾɾ���ɹ�
 */
int Play_Perst_DeleteByID(int ID) {
	if(rename(PLAY_DATA_FILE,PLAY_DATA_TEMP_FILE)<0){
		printf("Cannot open file Play.dat!\n");
		return 0;
	}	
	FILE *fpsour;/*��ʱ�ļ�*/
	FILE *fpTarg;//ԭ�����ļ�
	if((fpsour=fopen(PLAY_DATA_TEMP_FILE,"rb"))==NULL)
		{
		printf("Cannot open file Play.dat!\n");
        return 0;
    }
	if((fpTarg=fopen(PLAY_DATA_FILE,"wb"))==NULL)
		{
		printf("Cannot open file Play.dat!\n");
        return 0;
    }
	play_t buf;
	int Found=0;
	while(!feof(fpsour)){
	if(fread(&buf,sizeof(play_t),1,fpsour)){
		if(ID==buf.id){
			Found=1;
			continue;
		}
	fwrite(&buf,sizeof(play_t),1,fpTarg);
	}
}
fclose(fpsour);
fclose(fpTarg);
//ɾ����ʱ�ļ�
remove("PlayTmp.dat");
return Found;
}


/*
 * Function:    Play_Perst_SelectByID
 * Function ID:	TTMS_SCU_Play_Perst_SelByID
 * Description: ���վ�ĿID�Ų��Ҿ�Ŀ����Ϣ
 * Input:       �����ҵľ�ĿID�ţ�������ҽ�����ڴ�ĵ�ַ
 * Output:      ��
 * Return:      0��ʾδ�ҵ���1��ʾ�ҵ���
 */
int Play_Perst_SelectByID(int ID, play_t *buf) {
	assert(buf!=NULL);
	FILE *fp=fopen(PLAY_DATA_FILE,"rb");
	if(fp==NULL){
		printf("Cannot open file Play_t!\n");
		return 0;
	}
	
	play_t data;
	int Found=0;
	
	while(!feof(fp)){
		if(fread(&data,sizeof(play_t),1,fp)){
			if(ID==data.id){
				buf->id=data.id;
				Found=1;
				break;
			};
		}
	}
	fclose(fp);
	return Found;
}


/*
 * Function:    Play_Perst_SelectAll
 * Function ID:	TTMS_SCU_Play_Perst_SelAll
 * Description: �����о�Ŀ��Ϣ������һ������
 * Input:       list��Ŀ��Ϣ�����ͷָ��
 * Output:      ��ʾ��������ʱ������ռ�ʧ��
 * Return:      ���ز��ҵ��ļ�¼��Ŀ
 */
int Play_Perst_SelectAll(play_list_t list) {
	play_node_t *newNode;
	play_t data;
	int recCount=0;

	//arrest(list!=NULL);
	
	List_Free(list ,play_node_t);
	
	FILE *fp =fopen(PLAY_DATA_FILE,"rb");
	if(fp==NULL){
	printf("Cannot open file Play.data!");
	return 0;
	}
	while(!feof(fp)){
		if(fread(&data,sizeof(play_t),1,fp)){
			newNode=(play_node_t *)malloc(sizeof(play_node_t));
			if(!newNode){
				printf("warning, Memory OverFlow!!! \ncannot load more data into memory!!\n");
				break;
			}
			newNode->data=data;
			//List_Addtail(list,newNode);
			recCount++;
		}	
	}	
	fclose(fp);
	return recCount;
}


/*
 * Function:    Play_Perst_SelectByName
 * Function ID:	TTMS_SCU_Play_Perst_SelByName
 * Description: ���վ�Ŀ���Ʋ��Ҿ�Ŀ����Ϣ
 * Input:       listΪ���ҵ��ľ�Ŀ��Ϣ����condtΪģ����ѯ�Ĺؼ���
 * Output:      ��ʾ��������ʱ������ռ�ʧ��
 * Return:      ���ز��ҵ��ļ�¼��Ŀ
 */
int Play_Perst_SelectByName(play_list_t list, char condt[]) {
	int recCount=0;
	FILE *fp;
	play_node_t *newNode;
	//play_list_t p;
	//p=list;
	if((fp=fopen(PLAY_DATA_FILE,"rb"))==NULL){
		printf("cannot open file Play.dat!");
		return 0;
	}
	assert(list!=NULL);
	List_Free(list,play_node_t);
	play_t buf;
	while(!feof(fp)){

		if(fread(&buf,sizeof(play_t),1,fp)){

			if(strstr(buf.name,condt)!=NULL){
	
		newNode=(play_node_t *)malloc(sizeof(play_node_t));
		if(!newNode){
		 printf(
                        "Warning, Memory OverFlow!!!\n Cannot Load more Data into memory!!!\n");
                	break;
            	}
			newNode->data=buf;
			List_AddTail(list,newNode);
			recCount++;
		}
	}
	}
	fclose(fp);
	return recCount;
}
	//xiugai 
