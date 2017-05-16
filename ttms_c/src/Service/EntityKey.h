/*
 * EntityKey.h
 *
 *  Created on: 2015��6��13��
 *      Author: Administrator
 */

#ifndef ENTITYKEY_H_
#define ENTITYKEY_H_

typedef struct {
	char entyName[41];
	long key;
} entity_key_t;

typedef struct entity_key_node{
	entity_key_t data;
	struct entity_key_node *prev;
	struct entity_key_node *next;
}entkey_node_t, *entkey_list_t;

/*���ݴ����ʵ����entName��Ϊ��ʵ�����һ��Ψһ����������������ֵΪȡ��ʵ�������ֵ*/
inline long EntKey_Srv_CompNewKey(char entName[]);

/*���ݴ����ʵ����entName��ʵ�����count��Ϊ���count����ʵ�����һ������Ϊcount������ֵ���䣬
 * ʹ��ÿ����ʵ���ڸ������ڶ����Է��䵽 Ψһ�� ����������ֵΪ�������������Сֵ*/
inline long EntKey_Srv_CompNewKeys(char entName[], int count);

//������key���浽��������keyList��
inline void EntKey_Srv_Add2List(entkey_list_t keyList, long key);

//����������keyList�м��key�Ƿ���ڣ�����1���ڣ�����0
inline int EntKey_Srv_CheckExist(entkey_list_t keyList, long key);

#endif /* ENTITYKEY_H_ */
