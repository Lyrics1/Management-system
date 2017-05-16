/*
 *  Seat_UI.h
 *
 *  Created on: 2015��5��23��
 *  Author: lc
 */

#ifndef SEAT_UI_H_
#define SEAT_UI_H_
#include "../Common/list.h"
#include "../Service/Seat.h"

void Seat_UI_MgtEntry(int roomID);


//����״̬���ض�Ӧ��ʾ״̬����
inline char Seat_UI_Status2Char(seat_status_t status);

//����״̬���ŷ�����λ״̬
inline seat_status_t Seat_UI_Char2Status(char statusChar);


int Seat_UI_Add(seat_list_t list, int roomID, int rowsCount,int colsCount);

int Seat_UI_Modify(seat_list_t list, int rowsCount, int colsCount);

int Seat_UI_Delete(seat_list_t list, int rowsCount, int colsCount);

#endif /* SEAT_UI_H_ */
