import React, { createRef, useEffect } from "react";
import withRoot from '../modules/withRoot';
import './Gather.css';

function Mini_map() {

  let canvas;
  let context;
  let canvasRef = createRef();

  let backgroundReady = false;
  let backgroundImg = new Image();

  backgroundImg.src = "/assets/test.png";

  backgroundImg.onload = function () {
    backgroundReady = true;
  };

  let storeReady = false;
  let storeImg = new Image();

  storeImg.src = "/assets/download.jpg";

  storeImg.onload = function () {
    storeReady = true;
  };

  useEffect(() => {

    canvas = canvasRef.current;
    context = canvas.getContext("2d");

    // 마우스 클릭
    canvas.addEventListener('mousedown', e => {
      // 마우스 좌표 찾기
      var x = e.clientX;
      var y = e.clientY;
      var coords = "X coords: " + x + ", Y coords: " + y;
      console.log(coords);
      // 클릭 이벤트
      if (x >= 0 && x <= 1000 && y >= 0 && y <= 1000) {
        openWin()
        console.log('마우스 버튼 ON 이벤트 발생!');
      }
    });
  }, []);

  function loadImage() {
    if (backgroundReady) {
      context?.drawImage(backgroundImg, 0, 0);
    }
    if (storeReady) {
      context?.drawImage(storeImg, 150, 150);
    }
  }

  function gameLoop() {
    loadImage();
    requestAnimationFrame(gameLoop);
  }

  gameLoop();
  
  function openWin() {
    // 상품 구매 페이지  
    window.open("/item", "", "width=650, height=900, toolbar=no, menubar=no, scrollbars=no, resizable=yes");
  }

  return (
    <React.Fragment>
      <canvas ref={canvasRef}
      style={{ width: "69vw", height: "50vh" }}
        width="1500"
        height="925"
      />
    </React.Fragment>
  );
}


export default withRoot(Mini_map);