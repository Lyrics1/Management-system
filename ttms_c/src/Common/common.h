/*
 * common.h
 *
 *  Created on: 2015��4��25��
 *      Author: Administrator
 */

#ifndef COMMON_H_
#define COMMON_H_

typedef struct {
	int year;
	int month;
	int day;
}user_date_t;

typedef struct {
	int hour;
	int minute;
	int second;
}user_time_t;

//ֱ�Ӷ�ȡ��������ֵ
int ScanKeyboard();

//���ַ���str�͵�ת��Ϊ��д�ַ������������ַ���ͷָ��
char *Str2Upper(char *str);

//���ַ���str�͵�ת��ΪСд�ַ������������ַ���ͷָ��
char *Str2Lower(char *str);

//�Ƚ�����dt1, dt2�Ĵ�С����ȷ���0��dt1<dt2����-1������1
int DateCmp(user_date_t dt1, user_date_t dt2);

//��ȡϵͳ��ǰ����
user_date_t DateNow();

//��ȡϵͳ��ǰʱ��
user_time_t TimeNow();

#endif /* COMMON_H_ */
