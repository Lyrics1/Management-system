/*
 *account.h
 *
 *  Created on: 2015��5��8��
 *      Author: Administrator
 */
#ifndef ACCOUNT_H_
#define ACCOUNT_H_

//����ö������account_type_t�������û��Ľ�ɫ
typedef enum {
	USR_ANOMY = 0, //USR_ANOMY  �����û�
	USR_CLERK =1, //USR_CLERK  ��ƱԱ
	USR_MANG = 2, //USR_MANG  ����
	USR_ADMIN = 9 //USR_ADMIN  ϵͳ����Ա
} account_type_t;

//����ϵͳ�û��˺��������ͣ������û��˺���Ϣ
typedef struct {
	int  id;//�û�id
	account_type_t type;//�û�����
	char username[30];//�û���
	char password[30];//�û�����
} account_t;

//����ϵͳ�û��˺�˫������
typedef struct account_node {
	account_t data;
	struct account_node *next, *prev;
} account_node_t, *account_list_t;



void Account_Srv_InitSys();

int Account_Srv_Verify(char usrName[], char pwd[]);

inline int Account_Srv_Add(const account_t *data);

inline int Account_Srv_Modify(const account_t *data);

inline int Account_Srv_DeleteByID(int usrID);

inline int Account_Srv_FetchByID(int usrID, account_t *buf);

inline int Account_Srv_FetchByName(char usrName[], account_t *buf);

inline int Account_Srv_FetchAll(account_list_t list);

account_node_t * Account_Srv_FindByUsrName(account_list_t list, char usrName[]);


#endif //ACCOUNT_H_
