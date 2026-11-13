import os
cad=open('cadet.svgfrag').read()
DEFS='<defs><linearGradient id="green" x1="0" y1="0" x2="1" y2="1"><stop offset="0" stop-color="#0E4D2E"/><stop offset="1" stop-color="#05301B"/></linearGradient></defs>'
STR=''.join(f'<line x1="{x}" y1="0" x2="{x-700}" y2="1920"/>' for x in range(0,1800,30))
items=[
 ('home','Crack the Sainik School Exam 2027','AISSEE Class 6 &amp; Class 9 — complete preparation in one app'),
 ('mock','Full-Length AISSEE Mock Tests','Real exam pattern • live timer • sections • no negative marking'),
 ('palette','Feel the Real Exam Hall','Question palette: answered, not answered, marked for review'),
 ('quiz','Instant Answers + Explanations','Unlimited chapter-wise practice with step-by-step solutions'),
 ('solutions','Previous Year Pattern Papers','2016–2025 practice sets, subject-wise, fully solved'),
 ('result','Track Score &amp; Progress','Subject-wise analysis &amp; answer review after every test'),
 ('chapter','Chapter-wise Notes','Maths • Reasoning • English • GK • Science • Social Science'),
 ('dates','AISSEE 2027 Dates &amp; Admission','Eligibility, exam pattern, syllabus, fees, schools &amp; counselling'),
]
phone_src={'home':'p_home','mock':'p_mock','palette':'p_palette','quiz':'p_quiz2','solutions':'p_solutions','result':'p_result','chapter':'p_chapter','dates':'p_dates'}
tab_src={k:'t10_'+k for k in phone_src}; tab_src['quiz']='t10_quiz'
def page(img,title,sub,tablet,idx):
    sw = 860 if tablet else 760
    return f'''<!doctype html><html><head><meta charset="utf-8"><style>
html,body{{margin:0;width:1080px;height:1920px;overflow:hidden}}
body{{background:#2a301c;font-family:"Segoe UI",Roboto,Arial,sans-serif;position:relative}}
.band{{position:absolute;left:0;right:0;top:0;height:14px;background:linear-gradient(90deg,#FF9933 0 33.3%,#fff 33.3% 66.6%,#138808 66.6%)}}
.hd{{position:absolute;top:44px;left:50px;right:50px;display:flex;flex-direction:column;align-items:center;gap:18px}}
@font-face{{font-family:Imp;src:url("file:///C:/Windows/Fonts/impact.ttf")}}
.bgc{{position:absolute;inset:0}}
.badge{{display:flex;white-space:nowrap;align-items:center;gap:16px;font-family:Imp,Impact;color:#fff;font-size:52px;letter-spacing:1px;background:#C8102E;border:3px solid #fff;border-radius:12px;padding:4px 22px 4px 8px}}
.badge img{{width:76px;height:76px;border-radius:14px}}
h1{{margin:0;text-align:center;color:#fff;font-size:{70 if len(title)<30 else 62}px;line-height:1.1;font-weight:800}}
p{{margin:0 30px;text-align:center;color:#FFE08A;font-size:34px;line-height:1.3}}
.dev{{position:absolute;left:50%;top:{470 if tablet else 455}px;transform:translateX(-50%);width:{sw}px;padding:14px;background:#0a0f16;border-radius:{40 if tablet else 56}px;box-shadow:0 30px 80px rgba(0,0,0,.6),0 0 0 5px #F2B630}}
.dev img{{display:block;width:100%;border-radius:{28 if tablet else 44}px}}
.fade{{position:absolute;left:0;right:0;bottom:0;height:200px;background:linear-gradient(transparent,#05301B)}}
</style></head><body><svg class="bgc" width="1080" height="1920" viewBox="0 0 1080 1920">{DEFS}<rect width="1080" height="1920" fill="url(#green)"/><g opacity=".07" stroke="#fff" stroke-width="2">{STR}</g></svg><div class="band"></div>
<div class="hd"><div class="badge"><img src="v4/icon_badge.png">SAINIK SCHOOL</div>
<h1>{title}</h1><p>{sub}</p></div>
<div class="dev"><img src="shots/{img}.png"></div><div class="fade"></div></body></html>'''
for i,(k,t,s) in enumerate(items,1):
    open(f'ph_{i}.html','w',encoding='utf-8').write(page(phone_src[k],t,s,False,i))
    open(f'tb_{i}.html','w',encoding='utf-8').write(page(tab_src[k],t,s,True,i))
