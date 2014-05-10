$j = jQuery.noConflict();
$j(document).ready(function(){
	var contents = document.getElementsByClassName('content');
	var toggles = document.getElementsByClassName('type');

	var myAccordion = new fx.Accordion(
		toggles, contents, {opacity: true, duration: 400}
	);
	myAccordion.showThisHideOpen(contents[0]);
		//系统管理
		$j("#xtgl").click(function(){
			$j(".xtgl").slideToggle();
		});
		
		/*//用户管理
		$("#yhgl").click(function(){
			$(".yhgl").slideToggle();
		});
		//角色管理
		$("#jsgl").click(function(){
			$(".jsgl").slideToggle();
		});
		//权限管理
		$("#qxgl").click(function(){
			$(".qxgl").slideToggle();
		});
		//菜单管理
		$("#cdgl").click(function(){
			$(".cdgl").slideToggle();
		});
		//账号管理
		$("#zhgl").click(function(){
			$(".zhgl").slideToggle();
		});
		//库存管理
		$("#kcgl").click(function(){
			$(".kcgl").slideToggle();
		});
		//货物管理
		$("#hwgl").click(function(){
			$(".hwgl").slideToggle();
		});*/
		
		
		//基础信息管理
		$j("#jcxxgl").click(function(){
			$j(".jcxxgl").slideToggle();
		});
		//购买销售管理
		$j("#gmxsgl").click(function(){
			$j(".gmxsgl").slideToggle();
		});
		
		
		/*//采购管理
		$("#cggl").click(function(){
			$(".cggl").slideToggle();
		});
		//销售管理
		$("#xsgl").click(function(){
			$(".xsgl").slideToggle();
		});
		//客户管理
		$("#khgl").click(function(){
			$(".khgl").slideToggle();
		});*/
	});