FAZA I

*Numerical Code ( Adhurim )

Kodi eshte i perbere prej dy metodav, enkodimi dhe dekodimi. Te dy metodat si parameter hyres kane vetem nje parameter
hyres i tipit String, ndersa funksioni i trete i cili eshte seperator ai eshte i perfshire ne te dy keta metoda duke i 
ndare me spaces ( menyra e kesaj eshte bere duke deklaru nje array te stringave qe na paraqet spaces mes dy shkronjave 
apo numrave siq e kerkon komanda enkodimi apo dekodimi).
Metoda e pare enkodimit ka per qellim qe mesazhin te cilin ja japim per  ta enkoduar ta kthej ne numer. Ky numer tegron 
poziten (CharAt) e shkronjes ne Alfabet. Alfabetit nuk i'u kam qasur permes ASCII code, por e kam deklaruar is array i 
stringave duke i shkruar te gjitha shkronjat me rradhe, dhe i'u kam permbajtur alfabetit EN ).
Shembull i metodes se pare: Japim nje tekst qe na kerkon metoda p.sh: a b c d , dhe pas enkripitimit do te na kthej poztien
e ketyre ne alfabet qe do te jete: 1 2 3 4.
Ndersa sa i perket metodes se dyte, dekodimi, ka per qellim qe numrat 1-25 qe do t'ia japim per ta dekoduar te na kthej ne
tekst duke u bazuar ne te njejten menyre, pozita e shkronjes ne alfabet.
Shembull i metodes se dyte: Japim disa numra: 1 2 3 4, pergjiga do te na jepet ne baze  te dekodimit keshtu : a b c d.
Edhe ketu e kam perdorur aflabetin si array i stringut.
Literatura ku jam bazuar eshte kryesisht ne stackoverflow ( https://stackoverflow.com/questions/2057092/how-to-write-numerical-java-code ),
(https://stackoverflow.com/questions/2938482/encode-decode-a-long-to-a-string-using-a-fixed-set-of-letters-in-java).
 
*Play fair Command ( Besian )



Ka nje menyr interesante per paraqitjen e nje plaintexti ne ciphertext(te enkriptuar)  
I shikon shkronjat 2 nga 2 pra duhet qe fjalia te jete qift per , e marrim nje matric 5x5 ku e shenojm 
25 shkronja te alfabetiti duke e neglizhuar nje shkronje e cila menodjm se perdoret me pak ose egziston
 edhe menyra tjeter ku e shenojme nje qeles qeles i cili po aq shkronja sa ka ze pozitat e para te matrices
 pastaj duke vazhduar me alfabetin me rradhe. Pastaj marrim shkronjat 2 nga 2 nqfs kemi 2 shkronja te njejta
 eshte mire qe njera te zv me ndonje X(ose ndonje shkonje qe e din qe nuk perdoret shum) Nqfs 2 shkronjat jane
 ne menyre horizontale ather levizin poziten per 1 ne te djatht ndersa per vertikal levizin nje me poshte , 
ndersa epr diagonale levizin ne forme te katrorit , ndersa sa i perket dekriptimit ather eshte komplet e kunderta .
 Per ta plotsuar spjegimin tim dhe ta kupuar si duhet Ju lutem vizitoni [linkun](https://www.youtube.com/watch?v=-KjFbTK1IIw)
 reference/source:https://www.w3schools.com/  ,  https://stackoverflow.com/ , https://www.youtube.com/         https://www.geeksforgeeks.org/playfair-cipher-with-examples/,https://www.youtube.com/watch?v=bKPQnak-F3Q.
 
 
 *Vigenere Code ( Clirim )
 
 Per te perodorur koidn e Vigenere nevojiten nje celes dhe nje mesazh. Mesazhi dhe celesi duhet te jane te shtyupura me shkronja te alfabetit. Gjatesia e e celesit dhe e mesazhit nuk kane rendesi. Mesazhi dhe celesi kthehen ne shkronja te medha. Ne qofte se celesi eshte me i shkurter se mesazhi, nje funksion gjeneron celesion dhe madhesin e tije e rrite duke e perseritur ate celes derisa ta arrije gajtesine e mesazhit. Ja ne shembull: ky do te ishte mesazhiATTACKATDAWN dhe ky do te ishte celesi LEMON. Per te arritur gjatesine e mesazhit celesi merr kete forme LEMON. Arsyeje pse celesi duhet te jete i barabart me eshte se ne tabelen e Vigeneres gjehet prerja e secilis shkornje te celesit me shrkonjen e mesazhit. Shkronjat e mesazhit jane vertikalisht ne tabelen e Vigeneres si
dhe shkronjat e celesit jane te horizontalisht. Prandaj tabela formohet 26x26, dhe neper cdo fushe mbushet me shkronja duke u perserituralfabeti. Ne kete menyre i gjendet mesazhi i enkriptuar. Kurse Dekriptimi behet duke gjetur shkronjen e tekstit te enriputar ne rrehstin e pare te tabeles se Vigenere dhe ku do qe e gjen shkronjen e njejt ne ate rresht, mbi ate rresht vertikalisht gjendet shkronja e pare e plain textit.
reference/source: https://www.geeksforgeeks.org/vigenere-cipher/ https://www.thecrazyprogrammer.com/2017/08/vigenere-cipher-c-c.html https://www.braingle.com/brainteasers/codes/vigenere.php https://www.youtube.com/watch?v=Pc9MABiASO4
 
 FAZA II 

Kjo faze permban gjithsej 6 kerkesa. Me posht do i rradise secilin se si kompjallohen dhe ekzekutohen

-Create-User
  - Per ta krijuar nje cift publik te RSA me emra te cilet ja jep useri duhet te ndiqni keto hapa:
	1.Bej Run dhe pastaj edit configuration, 2. Jepi argumentet me rradhe si ne vijim: I. createdeleteuser, II.createuser, III."Emri i celsit"
 
-Delete-User
  - Per te fshire nje celes ekzistues te krijuar me heret duhet te ndiqni keto hapa:
	2.Bej Run dhe pastaj edit configuration, 2.Jepi argumentet me rradhe si ne vijim: I.createdeleteuser, II.delete, III."Emri i celsit"
(Dy kerkesat me siper i kemi ber me nje klase pasi qe ishte me e leht)

-Export-key
  - Per te eksportuar nje celes publik ose privat te shfrytzuesit nga direktoriumi i celsave ndiqni keto hapa:
	1.Bej Run dhe pastaj edit configuration, 2.Jep argumentet me rradhe si ne vijim: I.export_key, II.publik ose privat, III.from(file) IV.to(file)
 
-Import-key
  - Per te importuar celsin publik ose privat te shfrytzuesit nga shtegu ne direktoriumin e celsace, ndiqni keto hapa:
	1.Bej Run dhe pastaj edit configuration, 2.Jep argumentet me rradhe si ne vijim: I.Import_key, II.importo, III. emri
 
-Write-message
  - Per te shkruar nje mesazh te enkriptuar te dedikuar nje shfrytzuesi, duhet te ndiqni keto hapa:
	1.Bej Run dhe pastaj edit configuration, 2.Jep argumentet me rradhe si ne vijim: I.Writereadmessage, II.Encrypt, III.Celsi, IV.mesazhi, V.path(random')
 
-Read-message
  - Per ta dekriptuar nje mesazh, duhet te ndiqni keto hapa:
	1.Bej Run dhe pastaj edit configuration, 2.Jep argumentet me rradhe is ne vijim: I.Writereadmessage, II.Decryot, III.Mesazhi(file)
 (Dy kerkesat me siper i kemi ber me nje klase pasi qe ishte me e leht)
 
