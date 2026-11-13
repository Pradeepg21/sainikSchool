"""Builds the v4 (real parade photo, GODL-India) icon + feature graphic HTML pages."""
import os
os.chdir(os.path.dirname(os.path.abspath(__file__)))

c = open('cadet.svgfrag', encoding='utf-8').read()
if 'bushy' not in c:
    s = c.index(' <!-- plume'); e = c.index(' <!-- ears')
    plume = ''' <!-- plume (red hackle), bushy -->
 <g>
  <path d="M104 80 C 86 50, 92 10, 112 -16 C 112 14, 116 44, 118 80 Z" fill="#B5121F"/>
  <path d="M110 80 C 102 46, 112 6, 130 -20 C 126 12, 128 46, 126 80 Z" fill="#D7192A"/>
  <path d="M116 80 C 116 50, 132 18, 146 2 C 138 30, 134 56, 128 82 Z" fill="#E8303C"/>
  <path d="M112 -16 C 106 20, 108 50, 116 80 M130 -20 C 120 20, 120 50, 122 80" stroke="#7E0B14" stroke-width="1.6" fill="none"/>
  <path d="M100 40 l-8 -4 M98 22 l-7 -5 M134 36 l8 -6 M138 18 l7 -6 M104 60 l-8 -3" stroke="#FF6670" stroke-width="2"/>
 </g>
'''
    c = c[:s] + plume + c[e:]
    c = c.replace(' <!-- white gloves -->',
                  ' <path d="M92 214 C 84 232, 80 260, 78 300 L 76 372" stroke="#8C7444" stroke-width="3" fill="none"/>\n'
                  ' <path d="M208 214 C 216 232, 220 260, 222 300 L 224 372" stroke="#7E6638" stroke-width="3" fill="none"/>\n'
                  ' <!-- white gloves -->')
    open('cadet.svgfrag', 'w', encoding='utf-8').write(c)

scene_defs = '''
 <linearGradient id="sky" x1="0" y1="0" x2="0" y2="1"><stop offset="0" stop-color="#CFE3F2"/><stop offset="1" stop-color="#EEF2E6"/></linearGradient>
 <linearGradient id="grnd" x1="0" y1="0" x2="0" y2="1"><stop offset="0" stop-color="#C79C74"/><stop offset="1" stop-color="#A97A55"/></linearGradient>
 <linearGradient id="gold" x1="0" y1="0" x2="1" y2="1"><stop offset="0" stop-color="#FFE08A"/><stop offset=".5" stop-color="#F2B630"/><stop offset="1" stop-color="#C98A12"/></linearGradient>
 <linearGradient id="green" x1="0" y1="0" x2="1" y2="1"><stop offset="0" stop-color="#0E4D2E"/><stop offset="1" stop-color="#05301B"/></linearGradient>
 <filter id="blur2"><feGaussianBlur stdDeviation="1.6"/></filter>
 <filter id="blur1"><feGaussianBlur stdDeviation="0.8"/></filter>
 <style>@font-face{font-family:Imp;src:url("file:///C:/Windows/Fonts/impact.ttf")}</style>
'''
D = '<defs>' + c + scene_defs + '</defs>'
open('D.svgfrag', 'w', encoding='utf-8').write(D)


def scene(w, h):
    """Parade-ground scene filling 0..w x 0..h with two rows of cadets."""
    hz = h * 0.42
    rows = ''
    for i in range(-1, int(w / 34) + 2):
        rows += f'<use href="#cadet" x="{i*34}" y="{hz-62}" width="40" height="80" filter="url(#blur2)" opacity=".85"/>'
    for i in range(-1, int(w / 52) + 2):
        rows += f'<use href="#cadet" x="{i*52+20}" y="{hz-40}" width="60" height="120" filter="url(#blur1)"/>'
    return (f'<rect width="{w}" height="{h}" fill="url(#sky)"/>'
            f'<path d="M0 {hz-30} C {w*.15} {hz-70}, {w*.3} {hz-40}, {w*.45} {hz-75} C {w*.6} {hz-50}, {w*.8} {hz-80}, {w} {hz-45} L {w} {hz+10} L 0 {hz+10} Z" fill="#6E8F57" opacity=".85"/>'
            f'<rect y="{hz}" width="{w}" height="{h-hz}" fill="url(#grnd)"/>{rows}')


def pg(s):
    return f'<!doctype html><html><head><meta charset="utf-8"></head><body style="margin:0;background:transparent;overflow:hidden">{s}</body></html>'


stripes = lambda W, H, step, dx: ''.join(f'<line x1="{x}" y1="0" x2="{x-dx}" y2="{H}"/>' for x in range(0, W + dx, step))

# ---------- shared icon artwork (scene circle + banner), in 512 space ----------
icon_art = f'''<clipPath id="ic"><circle cx="256" cy="226" r="196"/></clipPath>
<g clip-path="url(#ic)"><image href="photo_icon.jpg" x="60" y="30" width="392" height="392" preserveAspectRatio="xMidYMid slice"/>
</g>
<circle cx="256" cy="226" r="196" fill="none" stroke="url(#gold)" stroke-width="10"/>
<rect x="28" y="372" width="456" height="98" rx="10" fill="#C8102E" stroke="#fff" stroke-width="5"/>
<rect x="38" y="382" width="436" height="78" rx="6" fill="none" stroke="#FFD34D" stroke-width="2"/>
<text x="256" y="449" text-anchor="middle" font-family="Imp, Impact" font-size="70" fill="#fff" textLength="400" lengthAdjust="spacingAndGlyphs">SAINIK SCHOOL</text>
<rect x="150" y="474" width="212" height="30" rx="15" fill="url(#gold)"/>
<text x="256" y="497" text-anchor="middle" font-family="Segoe UI, Arial" font-weight="800" font-size="20" letter-spacing="3" fill="#3A1E00">EXAM PREP</text>'''
icon_bg = f'<rect width="512" height="512" fill="url(#green)"/><g opacity=".14" stroke="#fff" stroke-width="2">{stripes(512, 512, 26, 200)}</g>'

icon = f'<svg xmlns="http://www.w3.org/2000/svg" width="512" height="512" viewBox="0 0 512 512">{D}{icon_bg}{icon_art}</svg>'
open('icon.svg', 'w', encoding='utf-8').write(icon)
open('icon.html', 'w', encoding='utf-8').write(pg(icon))

# adaptive layers: 432px canvas, art scaled into the visible circle
open('fg.html', 'w', encoding='utf-8').write(pg(
    f'<svg xmlns="http://www.w3.org/2000/svg" width="432" height="432" viewBox="0 0 512 512">{D}'
    f'<g transform="translate(256 256) scale(0.64) translate(-256 -268)">{icon_art}</g></svg>'))
open('adbg.html', 'w', encoding='utf-8').write(pg(
    f'<svg xmlns="http://www.w3.org/2000/svg" width="432" height="432" viewBox="0 0 512 512">{D}{icon_bg}</svg>'))
mono_art = f'''<circle cx="256" cy="226" r="196" fill="none" stroke="#fff" stroke-width="14"/>
<clipPath id="ic2"><circle cx="256" cy="226" r="190"/></clipPath>
<g clip-path="url(#ic2)" filter="url(#white)"><use href="#cadet" x="146" y="44" width="220" height="440"/></g>
<rect x="28" y="372" width="456" height="98" rx="10" fill="#fff"/>
<text x="256" y="452" text-anchor="middle" font-family="Imp, Impact" font-size="78" fill="#000" letter-spacing="1">SAINIK SCHOOL</text>'''
open('mono.html', 'w', encoding='utf-8').write(pg(
    f'<svg xmlns="http://www.w3.org/2000/svg" width="432" height="432" viewBox="0 0 512 512">{D}'
    f'<defs><filter id="white"><feColorMatrix type="matrix" values="0 0 0 0 1  0 0 0 0 1  0 0 0 0 1  0 0 0 1 0"/></filter>'
    f'<mask id="mm"><rect width="512" height="512" fill="#fff"/><text x="256" y="452" text-anchor="middle" font-family="Imp, Impact" font-size="70" fill="#000" textLength="400" lengthAdjust="spacingAndGlyphs">SAINIK SCHOOL</text></mask></defs>'
    f'<g transform="translate(256 256) scale(0.64) translate(-256 -268)">'
    f'<clipPath id="above2"><rect width="512" height="366"/></clipPath><circle cx="256" cy="226" r="196" fill="none" stroke="#fff" stroke-width="14" clip-path="url(#above2)"/>'
    f'<clipPath id="icm"><circle cx="256" cy="226" r="188"/></clipPath>'
    f'<clipPath id="above"><rect width="512" height="366"/></clipPath><g clip-path="url(#icm)"><g clip-path="url(#above)" filter="url(#white)"><use href="#cadet" x="106" y="26" width="300" height="600"/></g></g>'
    f'<rect x="28" y="372" width="456" height="98" rx="10" fill="#fff" mask="url(#mm)"/></g></svg>'))

# ---------- feature graphic 1024x500 ----------
cap = ('<g transform="translate(-34 -18)"><path d="M0 18 L34 4 L68 18 L34 32 Z" fill="#111"/>'
       '<path d="M14 24 L14 38 C 24 46, 44 46, 54 38 L54 24 L34 32 Z" fill="#222"/>'
       '<path d="M60 21 L60 40" stroke="#E2B33F" stroke-width="3"/><circle cx="60" cy="42" r="4" fill="#E2B33F"/></g>')
books = ('<g transform="translate(-30 -22)" stroke="#222" stroke-width="3" fill="#fff"><rect x="0" y="26" width="60" height="14" rx="2"/>'
         '<rect x="4" y="12" width="56" height="14" rx="2" fill="#F2C14E"/><rect x="0" y="-2" width="58" height="14" rx="2" fill="#7A1F2B"/></g>')
feat = f'''<svg xmlns="http://www.w3.org/2000/svg" width="1024" height="500" viewBox="0 0 1024 500">{D}
<rect width="1024" height="500" fill="url(#green)"/>
<g opacity=".08" stroke="#fff" stroke-width="2">{stripes(1024, 500, 24, 300)}</g>
<clipPath id="fc"><circle cx="790" cy="250" r="300"/></clipPath>
<g clip-path="url(#fc)"><image href="photo_feat.jpg" x="480" y="-50" width="620" height="611" preserveAspectRatio="xMidYMid slice"/>
</g>
<path d="M 636 517 A 308 308 0 0 1 572 32" fill="none" stroke="url(#gold)" stroke-width="16"/>
<path d="M 543 457 A 322 322 0 0 1 498 114" fill="none" stroke="#fff" stroke-width="5" opacity=".7"/>
<text x="56" y="66" font-family="Segoe UI, Arial" font-weight="700" font-size="22" letter-spacing="4" fill="#FFD34D">AISSEE 2027 • CLASS 6 &amp; 9</text>
<line x1="56" y1="96" x2="470" y2="96" stroke="#fff" stroke-opacity=".35" stroke-width="2"/>
<text x="52" y="200" font-family="Imp, Impact" font-size="112" fill="#fff" letter-spacing="1">SAINIK</text>
<text x="52" y="310" font-family="Imp, Impact" font-size="112" fill="#fff" letter-spacing="1">SCHOOL</text>
<text x="58" y="360" font-family="Segoe UI, Arial" font-weight="600" font-size="34" fill="#fff">Entrance Exam Preparation</text>
<line x1="56" y1="384" x2="470" y2="384" stroke="#fff" stroke-opacity=".35" stroke-width="2"/>
<g font-family="Segoe UI, Arial" font-weight="700" font-size="19">
 <rect x="56" y="406" width="140" height="38" rx="19" fill="#C8102E"/><text x="126" y="431" text-anchor="middle" fill="#fff">Mock Tests</text>
 <rect x="206" y="406" width="150" height="38" rx="19" fill="url(#gold)"/><text x="281" y="431" text-anchor="middle" fill="#3A1E00">PYQ Pattern</text>
 <rect x="366" y="406" width="140" height="38" rx="19" fill="#fff"/><text x="436" y="431" text-anchor="middle" fill="#0E4D2E">Notes + Plan</text>
</g>
<rect y="490" width="341" height="10" fill="#FF9933"/><rect x="341" y="490" width="342" height="10" fill="#fff"/><rect x="683" y="490" width="341" height="10" fill="#138808"/>
</svg>'''
open('feature.html', 'w', encoding='utf-8').write(pg(feat))
print('ok')
