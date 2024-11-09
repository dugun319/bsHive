<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<title>Tree</title>
<link rel="stylesheet" type="text/css" href="/css/kh_tui-tree-style.css" />
<link rel="stylesheet" type="text/css" href="/css/kh_docs_tree.css" />
<link rel="stylesheet" type="text/css" href="/css/dist/tui-tree.css" />
</head>
<script type="text/javascript">


	$(function() {
		$("#tui-tree-node-2").click(function() {
			location.href="/kh/admin/stdntList";
			});
	});
	
	$(function() {
		$("#tui-tree-node-3").click(function() {
			location.href="/kh/admin/empList?mbr_se=2";
			});
	});
	
	$(function() {
		$("#tui-tree-node-4").click(function() {
			location.href="/kh/admin/empList?mbr_se=3";
			});
	});
	
	$(function() {
		$("#tui-tree-node-6").click(function() {
			location.href="/kh/admin/appLctrList?lectureType=5";
			});
	});
	
	$(function() {
		$("#tui-tree-node-7").click(function() {
			location.href="/kh/admin/appLctrList?lectureType=0";
			});
	});
	
	$(function() {
		$("#tui-tree-node-8").click(function() {
			location.href="/kh/admin/lctrRoom";
			});
	});
	
	$(function() {
		$("#tui-tree-node-9").click(function() {
			location.href="/kh/admin/schList";
			});
	});
	
	$(function() {
		$("#tui-tree-node-10").click(function() {
			location.href="/kh/admin/prdocList";
			});
	});
	
	
	$(function() {
		$("#tui-tree-node-12").click(function() {
			location.href="/kh/admin/boardList?pst_clsf=1";
			});
	});
	
	$(function() {
		$("#tui-tree-node-13").click(function() {
			location.href="/kh/admin/boardList?pst_clsf=2";
			});
	});
	
	$(function() {
		$("#tui-tree-node-14").click(function() {
			location.href="/kh/admin/boardList?pst_clsf=3";
			});
	});
	
	$(function() {
		$("#tui-tree-node-15").click(function() {
			location.href="/kh/admin/boardList?pst_clsf=4";
			});
	});
</script>
    <body>
        <div class="code-html">
            <div id="tree" class="tui-tree-wrap"></div>
        </div>
        <script src="/css/dist/tui-tree.js"></script>
        <script class="code-js">

        var data = [
            {text: '인원관리', children: [
                {text: '학생관리'}, 							//tui-tree-node-2
                {text: '교수관리'}, 							//tui-tree-node-3
                {text: '직원관리'} 							//tui-tree-node-4
            ]},
            {text: '학사관리', children: [
                {text:'오프라인강의개설승인'}, 					//tui-tree-node-6
                {text:'온라인강의개설승인'}, 						//tui-tree-node-7
                {text:'강의실확인'}, 							//tui-tree-node-8
                {text:'장학금'},								//tui-tree-node-9
                {text:'재증명'}								//tui-tree-node-10
            ]},
            {text: '게시판관리', children: [
            	{text:'공지사항게시판'},						//tui-tree-node-12
                {text:'약관'},								//tui-tree-node-13
                {text:'FAQ'},								//tui-tree-node-14
                {text:'1:1문의게시판'}							//tui-tree-node-15
            ]}
            
            
            //{text: '게시판관리', state:'closed', children: [
            	//{text:'공지사항게시판'},							//tui-tree-node-13
                //{text:'약관'},									//tui-tree-node-14
                //{text:'FAQ'},									//tui-tree-node-15
                //{text:'1:1문의게시판'}							//tui-tree-node-16
            //]}
        ];

        var tree = new tui.Tree('#tree', {
            data: data,
            nodeDefaultState: 'opened'
        });

        </script>
    </body>
</html>