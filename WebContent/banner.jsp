<%@ page language="java" import ="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <div id="wrap">
        <div class="box">
            <ul class="banner clearfix">
                <li class='on banner_li'><img src="images/1.jpg" alt="Tmall" width="" height=""/></li>
                <li class="banner_li"><img src="images/2.jpg" alt="Tmall" width="" height=""/></li>
                <li class="banner_li"><img src="images/3.jpg" alt="Tmall" width="" height=""/></li>
                <li class="banner_li"><img src="images/4.jpg" alt="Tmall" width="" height=""/></li>
                <li class="banner_li"><img src="images/5.jpg" alt="Tmall" width="" height=""/></li>
                <li class="banner_li"><img src="images/6.jpg" alt="Tmall" width="" height=""/></li>
                <li class="banner_li"><img src="images/1.jpg" alt="Tmall" width="" height=""/></li>
            </ul>
            <div class="but">
                <button class="on">1</button>
                <button>2</button>
                <button>3</button>
                <button>4</button>
                <button>5</button>
                <button>6</button>
            </div>
            <a class='prev banner_a' href="javascript:void(0);"></a>
            <a class='next banner_a' href="javascript:void(0);"></a>
        </div>
    </div>
    <script src='js/animation.js'></script>
    <script type="text/javascript">
    var oWrap = document.getElementById('wrap');
    var oUl = document.getElementsByClassName('banner')[0];
    var oLi = document.getElementsByClassName('banner_li');
    var oImg = document.getElementsByTagName('img');
    var oA = document.getElementsByClassName('banner_a');
    var aBut = document.getElementsByTagName('button');
    var num = 0;
    oUl.style.width = 1226*oImg.length+'px';
    function autoPlay(){
        timer = setInterval(function(){
            aBut[num%aBut.length].className = '';
            if(num == oImg.length-1){
                oUl.style.left = 0;
                num = 0;
            }
            num++;
            aBut[num%aBut.length].className = 'on';
            animation(oUl,{left:-num*1226},1000);
        },2000);
    }
    autoPlay();
    //右点击
    oA[1].onclick = function(){
        aBut[num%aBut.length].className = '';
        if(num == oImg.length-1){
            oUl.style.left = 0;
            num = 0;
        }
        num++;
        animation(oUl,{left:-num*1226},1000);
        aBut[num%aBut.length].className = 'on';
    }
    //左点击
    oA[0].onclick = function(){
        aBut[num%aBut.length].className = '';
        if(num == 0){
            num = oImg.length-1;
            oUl.style.left = -num*1226+'px';
        }
        num--;
        animation(oUl,{left:-num*1226},1000);
        aBut[num%aBut.length].className = 'on';
    }
    //点击按钮
    for(var i=0;i<aBut.length;i++){
        aBut[i].index = i;
        aBut[i].onclick = function(){
            aBut[num].className = '';
            num = this.index;
            animation(oUl,{left:-this.index*1226},1000);
            aBut[num].className = 'on';
        }
    }
    oWrap.onmouseenter = function(){clearInterval(timer);}
    oWrap.onmouseleave = function(){autoPlay();}
	</script>
    
    
    