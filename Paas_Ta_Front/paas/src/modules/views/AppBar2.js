import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import AppBar from '../components/AppBar';
import Toolbar from '../components/Toolbar';
import React, { useEffect, useState } from 'react';
import axios from 'axios'
import Notifications from "./Notifications";
import './AppBar2.css';



function AppAppBar2() {
  const session = JSON.parse(window.sessionStorage.getItem("data"));

  let cart = {
    count: 5,
    cost: 500,
  };

  var list= new Array();
  var data = new Object();

    data.message = '고등어(샘플) : '+cart.count;
    list.push(data);
    data = new Object();
    data.message = '오징어(샘플) : '+cart.count;
    list.push(data);
    data = new Object();
    data.message = '💰 총 결재 금액 : '+cart.cost;
    data.detailPage = '/Cart'
    list.push(data);


  // let sample=([
  //   {
  //     message: list
  //   },
  //   {
  //     message: '💰 총 결재 금액 : '+cart.cost,
  //     detailPage : '/Cart'
  //   },
  // ]);

  return (
    <div>
      <AppBar position="fixed">

        <Toolbar sx={{ justifyContent: 'space-between' }}>
          <Box sx={{ flex: 1 }} />

          <Link
            variant="h6"
            underline="none"
            color="inherit"
            href="/Town"
            sx={{ fontSize: 25 }}
          >
            {'Gather skon(임시 제목)'}

          </Link>
          <Box sx={{ flex: 1, display: 'flex', justifyContent: 'flex-end' }}>
            {/* <Link
              variant="h5"
              underline="none"
              //href="/MyPage"
              sx={{ ...rightLink, color: 'lightgreen' }}
            >
              {session_name.email + ' 님 환영합니다.'}
            </Link> */}

    <ul><li>
    <Notifications
    data={list}

      //  data={[
      //     {
      //       message: list,
      //       detailPage : '/Cart'
      //     },
      //     {
      //       message: '💰 총 결재 금액 : '+cart.cost,
      //       detailPage : '/Cart'
      //     },
      //   ]}
        headerBackgroundColor = 'white'
        header={
          {
            title: '👜 장바구니',
            option: { text: '구매하기', onClick: () => {window.location = "./Cart"} }
          }
        }
    />
    </li>
    </ul>
          </Box>
        </Toolbar>
      </AppBar>
      <Toolbar />
    </div>
  );
}

export default AppAppBar2;