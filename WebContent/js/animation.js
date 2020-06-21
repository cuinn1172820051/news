function animation(obj,json,dura){
            var init = {};//初识值对象
            var target = {};//目标对象
            for(attr in json){
                init[attr] = parseFloat(getStyle(obj,attr));
                target[attr] = parseFloat(json[attr]);
            }
            var init_t = new Date();
            var run_val;//运动距离
            var run_t;  //运动时间
            var a;       //运动加速度
            (function reAnimation(){
                run_t = new Date() - init_t;
                if(run_t > dura){
                        run_t = dura;
                    }else{
                        requestAnimationFrame(reAnimation);
                    }
                for(attr in json){
                    a = (target[attr] - init[attr])*2/Math.pow(dura,2);
                    run_val = a*Math.pow(run_t,2)/2;
                    obj.style[attr] = init[attr]+run_val+(attr == 'opacity'?'':'px');
                }
            })();
        }
        function getStyle(obj,attr){
            return window.getComputedStyle?window.getComputedStyle(obj)[attr]:obj.currentStyle[attr];
        }
