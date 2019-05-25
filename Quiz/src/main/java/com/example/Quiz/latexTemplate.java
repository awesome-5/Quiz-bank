package com.example.Quiz;

public class latexTemplate {
	
String coverPageTestFinal ="\\documentclass[a4paper,11pt]{article}\r\n" + 
		"\\usepackage{xcolor}\r\n" + 
		"\\usepackage{wits_code}\r\n" + 
		"\\usepackage{wits_exam}\r\n" + 
		"\\usepackage{float}\r\n" + 
		"\\usepackage{graphicx}\r\n" + 
		"\\usepackage{array}\r\n" + 
		"\\usepackage{wits_flowchart}\r\n" + 
		"\\usepackage{wits_pseudocode}\r\n" + 
		"\\usepackage{amsmath}\r\n" + 
		"\\usepackage{charter}\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\\usepackage[]{color}\r\n" + 
		"\\usepackage{float}\r\n" + 
		"\\usepackage{subcaption}\r\n" + 
		"\\usepackage{varioref}\r\n" + 
		"\\usepackage{hyperref}\r\n" + 
		"\\usepackage{cleveref}\r\n" + 
		"\r\n" + 
		"\\definecolor{darkgreen}{rgb}{0.0,0.7,0.0}\r\n" + 
		"\\hypersetup{\r\n" + 
		"  colorlinks   = true,              %Colours links instead of ugly boxes\r\n" + 
		"  urlcolor     = blue,              %Colour for external hyperlinks\r\n" + 
		"  linkcolor    = blue,              %Colour of internal links\r\n" + 
		"  citecolor    = darkgreen                %Colour of citations\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\\newcommand{\\todo}{\\textbf{TODO}}\r\n" + 
		"\\titleHeadTime{2}\r\n" + 
		"\\titleHeadDay{1}\r\n" + 
		"\\titleHeadMonth{Mar}\r\n" + 
		"\\titleHeadYear{2018}\r\n" + 
		"\\titleHeadVenue{CB Exams Hall}\r\n" + 
		"% First set up fieled that the exams office wants\r\n" + 
		"\\courseno"+"{"+HomePage.CurrentCourse+"}\r\n" + 
		"\\papertitle"+"{"+HomePage.CurrentCourseName+"}\r\n" +  
		"\\testmonth{March 1}\r\n" + 
		"\\testyear{2018}\r\n" + 
		"%\\yearofstudy{1} % None for Arts and Science\r\n" + 
		"\\degrees{BSc, BSc (Applied Computing), BEconSci}\r\n" + 
		"\\faculties{Science}\r\n" + 
		"\\internalexaminer{Mr Steven James\\\\x-76157}\r\n" + 
		"\\externalexaminer{Prof. Stefan Gruner (UP)}\r\n" + 
		"\\specialmaterial{None}\r\n" + 
		"\\hoursallowance{2 Hours}\r\n" + 
		"\\instructions{\\thetotalmarks\\ Marks available. \\thetotalmarks\\ marks = 100\\%.\\\\ Answer all questions. This is a closed book exam. This exam consists of 11 pages.}\r\n" + 
		"%% Following field not needed for \\examcover but for \\simpleexamhead\r\n" + 
		"%\\department{Computer Science}\r\n" + 
		"%\\solutiontitle{Mark scheme}\r\n" + 
		"\r\n" + 
		"%\\newcommand{\\ansline}{\\\\[15pt]\\rule{\\linewidth}{0.1pt}}\r\n" + 
		"%\\newcommand{\\anslineL}{\\rule{\\linewidth}{0.1pt}}\r\n" + 
		"    \r\n" + 
		"\\usepackage{titling}\r\n" + 
		"\\setlength{\\droptitle}{-7em}   % This is your set screw\r\n" + 
		"\\title"+"{"+HomePage.CurrentCourseName+" ("+HomePage.CurrentCourse+") \\\\ Class Test 1}\r\n" + 
		"\\date{1 March 2018, 14h15--16h15, Exams Hall}\r\n" + 
		"\\begin{document}\r\n" + 
		"%\\makeexamcover\r\n" + 
		"\\maketitle\r\n" + 
		"\r\n" + 
		"\\begin{tabular}{lll}\r\n" + 
		"\r\n" + 
		"Name: \\makebox[2in]{\\hrulefill} & Row: \\makebox[0.5in]{\\hrulefill} ~~Seat: \\makebox[0.5in]{\\hrulefill} & Signature: \\makebox[0.8in]{\\hrulefill}   \\\\ \\\\\r\n" + 
		" Student Number: \\makebox[1.2in]{\\hrulefill} & ID Number: \\makebox[1.2in]{\\hrulefill} & \\\\\r\n" + 
		"\r\n" + 
		"\\end{tabular}\r\n" + 
		"\r\n" + 
		"\\vspace*{10mm}\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\\centerline{\\textbf{For marking purposes only}}\r\n" + 
		"\\begin{center}\r\n" + 
		"{\\renewcommand{\\arraystretch}{1.4} %<- modify value to suit your needs\r\n" + 
		"\\begin{tabular}{|c|c|}\r\n" + 
		"\\hline \r\n" + 
		"Question 1 & \\hspace*{10mm} \\\\  \\hline\r\n" + 
		"Question 2 & \\\\ \\hline\r\n" + 
		"Question 3 & \\\\ \\hline\r\n" + 
		"Question 4 & \\\\ \\hline\r\n" + 
		"%Question 5 & \\\\ \\hline\r\n" + 
		"Total & \\\\ \\hline\r\n" + 
		"\\end{tabular}\r\n" + 
		"}\r\n" + 
		"\\end{center}\r\n" + 
		"\r\n" + 
		"\\section*{Instructions}\r\n" + 
		"\r\n" + 
		"\\begin{itemize}\r\n" + 
		"\\item Answer all questions in pen. \\textbf{Do not write in pencil.}\r\n" + 
		"\\item This test consists of $11$ pages. Ensure that you are not missing any pages.\r\n" + 
		"\\item This is a \\textbf{closed-book} test: you may not consult any written material or notes.\r\n" + 
		"\\item You are allocated 2 hours to complete this test.\r\n" + 
		"\\item There are $4$ questions and $50$ marks available.\r\n" + 
		"\\item Ensure your cellphone is switched off.\r\n" + 
		"\\end{itemize}\r\n" + 
		"\r\n" + 
		"\\section*{Flowchart Symbols}\r\n" + 
		"\r\n" + 
		"\\begin{minipage}{\\linewidth}\r\n" + 
		"{\\bf Basic symbols used in flowcharts}\r\n" + 
		"\\centering\r\n" + 
		"\\renewcommand{\\tabcolsep}{2pt}\r\n" + 
		"\\newcolumntype{P}{>{\\centering\\arraybackslash} m{32pt} } \r\n" + 
		"\\newcolumntype{A}{>{\\centering\\arraybackslash} m{32pt} } \r\n" + 
		"\\newcolumntype{T}{>{\\arraybackslash} m{272pt} } \r\n" + 
		"\\begin{tabular}{PAT}\r\n" + 
		"\\begin{tikzpicture} \\node[startstop] {}; \\end{tikzpicture} & $\\rightarrow$ & Start/Stop the program \\\\\r\n" + 
		"\\begin{tikzpicture} \\node[process] {}; \\end{tikzpicture} & $\\rightarrow$ & Process, Instruction \\\\\r\n" + 
		"\\begin{tikzpicture} \\node[io] {}; \\end{tikzpicture} & $\\rightarrow$ & Input/Output \\\\		\r\n" + 
		"\\begin{tikzpicture} \\node[decision] {}; \\end{tikzpicture} & $\\rightarrow$ & Decision, Question (use in branching) \\\\\r\n" + 
		"\\begin{tikzpicture} \\node[connector] {}; \\end{tikzpicture} & $\\rightarrow$ & Connector (connect one part of the flowchart to another) \\\\	\r\n" + 
		"\\end{tabular}\r\n" + 
		"\\end{minipage}\r\n" + 
		"\r\n" + 
		"\\pagebreak\r\n" + 
		"\r\n";

String coverPageTestDraft ="\\documentclass[a4paper,11pt]{article}\r\n" + 
		"\\usepackage{xcolor}\r\n" + 
		"\\usepackage{wits_code}\r\n" + 
		"\\usepackage{wits_exam}\r\n" + 
		"\\usepackage{float}\r\n" + 
		"\\usepackage{graphicx}\r\n" + 
		"\\usepackage{array}\r\n" + 
		"\\usepackage{wits_flowchart}\r\n" + 
		"\\usepackage{wits_pseudocode}\r\n" + 
		"\\usepackage{amsmath}\r\n" + 
		"\\usepackage{charter}\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\\usepackage[]{color}\r\n" + 
		"\\usepackage{float}\r\n" + 
		"\\usepackage{subcaption}\r\n" + 
		"\\usepackage{varioref}\r\n" + 
		"\\usepackage{hyperref}\r\n" + 
		"\\usepackage{cleveref}\r\n" + 
		"\r\n" + 
		"\\definecolor{darkgreen}{rgb}{0.0,0.7,0.0}\r\n" + 
		"\\hypersetup{\r\n" + 
		"  colorlinks   = true,              %Colours links instead of ugly boxes\r\n" + 
		"  urlcolor     = blue,              %Colour for external hyperlinks\r\n" + 
		"  linkcolor    = blue,              %Colour of internal links\r\n" + 
		"  citecolor    = darkgreen                %Colour of citations\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\\newcommand{\\todo}{\\textbf{TODO}}\r\n" + 
		"\\titleHeadTime{2}\r\n" + 
		"\\titleHeadDay{1}\r\n" + 
		"\\titleHeadMonth{Mar}\r\n" + 
		"\\titleHeadYear{2018}\r\n" + 
		"\\titleHeadVenue{CB Exams Hall}\r\n" + 
		"% First set up fieled that the exams office wants\r\n" + 
		"\\courseno"+"{"+HomePage.CurrentCourse+"}\r\n" + 
		"\\papertitle"+"{"+HomePage.CurrentCourseName+"}\r\n" +  
		"\\testmonth{March 1}\r\n" + 
		"\\testyear{2018}\r\n" + 
		"%\\yearofstudy{1} % None for Arts and Science\r\n" + 
		"\\degrees{BSc, BSc (Applied Computing), BEconSci}\r\n" + 
		"\\faculties{Science}\r\n" + 
		"\\internalexaminer{Mr Steven James\\\\x-76157}\r\n" + 
		"\\externalexaminer{Prof. Stefan Gruner (UP)}\r\n" + 
		"\\specialmaterial{None}\r\n" + 
		"\\hoursallowance{2 Hours}\r\n" + 
		"\\instructions{\\thetotalmarks\\ Marks available. \\thetotalmarks\\ marks = 100\\%.\\\\ Answer all questions. This is a closed book exam. This exam consists of 11 pages.}\r\n" + 
		"%% Following field not needed for \\examcover but for \\simpleexamhead\r\n" + 
		"%\\department{Computer Science}\r\n" + 
		"%\\solutiontitle{Mark scheme}\r\n" + 
		"\r\n" + 
		"%\\newcommand{\\ansline}{\\\\[15pt]\\rule{\\linewidth}{0.1pt}}\r\n" + 
		"%\\newcommand{\\anslineL}{\\rule{\\linewidth}{0.1pt}}\r\n" + 
		"    \r\n" + 
		"\\usepackage{titling}\r\n" + 
		"\\setlength{\\droptitle}{-7em}   % This is your set screw\r\n" + 
		"\\title"+"{"+HomePage.CurrentCourseName+" ("+HomePage.CurrentCourse+") \\\\ Class Test 1}\r\n" + 
		"\\date{1 March 2018, 14h15--16h15, Exams Hall}\r\n" + 
		"\\usepackage{draftwatermark}\r\n" + 
		"\\SetWatermarkText{Draft}\r\n" + 
		"\\SetWatermarkScale{2}\r\n"+
		"\\begin{document}\r\n" + 
		"%\\makeexamcover\r\n" + 
		"\\maketitle\r\n" + 
		"\r\n" + 
		"\\begin{tabular}{lll}\r\n" + 
		"\r\n" + 
		"Name: \\makebox[2in]{\\hrulefill} & Row: \\makebox[0.5in]{\\hrulefill} ~~Seat: \\makebox[0.5in]{\\hrulefill} & Signature: \\makebox[0.8in]{\\hrulefill}   \\\\ \\\\\r\n" + 
		" Student Number: \\makebox[1.2in]{\\hrulefill} & ID Number: \\makebox[1.2in]{\\hrulefill} & \\\\\r\n" + 
		"\r\n" + 
		"\\end{tabular}\r\n" + 
		"\r\n" + 
		"\\vspace*{10mm}\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\\centerline{\\textbf{For marking purposes only}}\r\n" + 
		"\\begin{center}\r\n" + 
		"{\\renewcommand{\\arraystretch}{1.4} %<- modify value to suit your needs\r\n" + 
		"\\begin{tabular}{|c|c|}\r\n" + 
		"\\hline \r\n" + 
		"Question 1 & \\hspace*{10mm} \\\\  \\hline\r\n" + 
		"Question 2 & \\\\ \\hline\r\n" + 
		"Question 3 & \\\\ \\hline\r\n" + 
		"Question 4 & \\\\ \\hline\r\n" + 
		"%Question 5 & \\\\ \\hline\r\n" + 
		"Total & \\\\ \\hline\r\n" + 
		"\\end{tabular}\r\n" + 
		"}\r\n" + 
		"\\end{center}\r\n" + 
		"\r\n" + 
		"\\section*{Instructions}\r\n" + 
		"\r\n" + 
		"\\begin{itemize}\r\n" + 
		"\\item Answer all questions in pen. \\textbf{Do not write in pencil.}\r\n" + 
		"\\item This test consists of $11$ pages. Ensure that you are not missing any pages.\r\n" + 
		"\\item This is a \\textbf{closed-book} test: you may not consult any written material or notes.\r\n" + 
		"\\item You are allocated 2 hours to complete this test.\r\n" + 
		"\\item There are $4$ questions and $50$ marks available.\r\n" + 
		"\\item Ensure your cellphone is switched off.\r\n" + 
		"\\end{itemize}\r\n" + 
		"\r\n" + 
		"\\section*{Flowchart Symbols}\r\n" + 
		"\r\n" + 
		"\\begin{minipage}{\\linewidth}\r\n" + 
		"{\\bf Basic symbols used in flowcharts}\r\n" + 
		"\\centering\r\n" + 
		"\\renewcommand{\\tabcolsep}{2pt}\r\n" + 
		"\\newcolumntype{P}{>{\\centering\\arraybackslash} m{32pt} } \r\n" + 
		"\\newcolumntype{A}{>{\\centering\\arraybackslash} m{32pt} } \r\n" + 
		"\\newcolumntype{T}{>{\\arraybackslash} m{272pt} } \r\n" + 
		"\\begin{tabular}{PAT}\r\n" + 
		"\\begin{tikzpicture} \\node[startstop] {}; \\end{tikzpicture} & $\\rightarrow$ & Start/Stop the program \\\\\r\n" + 
		"\\begin{tikzpicture} \\node[process] {}; \\end{tikzpicture} & $\\rightarrow$ & Process, Instruction \\\\\r\n" + 
		"\\begin{tikzpicture} \\node[io] {}; \\end{tikzpicture} & $\\rightarrow$ & Input/Output \\\\		\r\n" + 
		"\\begin{tikzpicture} \\node[decision] {}; \\end{tikzpicture} & $\\rightarrow$ & Decision, Question (use in branching) \\\\\r\n" + 
		"\\begin{tikzpicture} \\node[connector] {}; \\end{tikzpicture} & $\\rightarrow$ & Connector (connect one part of the flowchart to another) \\\\	\r\n" + 
		"\\end{tabular}\r\n" + 
		"\\end{minipage}\r\n" + 
		"\r\n" + 
		"\\pagebreak\r\n" + 
		"\r\n";

String coverPageExamFinal ="\\documentclass[a4paper,11pt]{article}\r\n" + 
		"\\usepackage{xcolor}\r\n" + 
		"\\usepackage{wits_code}\r\n" + 
		"\\usepackage{wits_exam}\r\n" + 
		"\\usepackage{float}\r\n" + 
		"\\usepackage{graphicx}\r\n" + 
		"\\usepackage{array}\r\n" + 
		"%\\usepackage{wits_flowchart}\r\n" + 
		"%\\usepackage{wits_pseudocode}\r\n" + 
		"\\usepackage{amsmath}\r\n" + 
		"\\usepackage{charter}\r\n" + 
		"\\usepackage{algpseudocode}\r\n" + 
		"\r\n" + 
		"\\usepackage[]{color}\r\n" + 
		"\\usepackage{float}\r\n" + 
		"\\usepackage{subcaption}\r\n" + 
		"\\usepackage{varioref}\r\n" + 
		"\\usepackage{hyperref}\r\n" + 
		"\\usepackage{cleveref}\r\n" + 
		"\r\n" + 
		"\\definecolor{darkgreen}{rgb}{0.0,0.7,0.0}\r\n" + 
		"\\hypersetup{\r\n" + 
		"  colorlinks   = true,              %Colours links instead of ugly boxes\r\n" + 
		"  urlcolor     = blue,              %Colour for external hyperlinks\r\n" + 
		"  linkcolor    = blue,              %Colour of internal links\r\n" + 
		"  citecolor    = darkgreen                %Colour of citations\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\\newcommand{\\todo}{\\textbf{TODO}}\r\n" + 
		"\\titleHeadTime{2}\r\n" + 
		"\\titleHeadDay{4}\r\n" + 
		"\\titleHeadMonth{June}\r\n" + 
		"\\titleHeadYear{2018}\r\n" + 
		"\\titleHeadVenue{Old Mutual Sports Hall}\r\n" + 
		"% First set up fieled that the exams office wants\r\n" + 
		"\\courseno"+"{"+HomePage.CurrentCourse+"}"+"\r\n" + 
		"\\papertitle"+"{"+HomePage.CurrentCourseName+"}"+"\r\n" + 
		"\\testmonth{June}\r\n" + 
		"\\testyear{2018}\r\n" + 
		"%\\yearofstudy{1} % None for Arts and Science\r\n" + 
		"\\degrees{BSc, BSc (Applied Computing), BEconSci}\r\n" + 
		"\\faculties{Science}\r\n" + 
		"\\internalexaminer{Mr Steven James\\\\x-76157}\r\n" + 
		"\\externalexaminer{Prof. Stefan Gruner (UP)}\r\n" + 
		"\\specialmaterial{None}\r\n" + 
		"\\hoursallowance{2 Hours}\r\n" + 
		"\\instructions{\\thetotalmarks\\ Marks available. \\thetotalmarks\\ marks = 100\\%.\\\\ Answer all questions. This is a closed book exam. This exam consists of 12 pages, including this cover page.}\r\n" + 
		"%% Following field not needed for \\examcover but for \\simpleexamhead\r\n" + 
		"%\\department{Computer Science}\r\n" + 
		"%\\solutiontitle{Mark scheme}\r\n" + 
		"\r\n" + 
		"%\\newcommand{\\ansline}{\\\\[15pt]\\rule{\\linewidth}{0.1pt}}\r\n" + 
		"%\\newcommand{\\anslineL}{\\rule{\\linewidth}{0.1pt}}\r\n" + 
		"    \r\n" + 
		"\\usepackage{titling}\r\n" + 
		"\\setlength{\\droptitle}{-7em}   % This is your set screw\r\n" + 
		"\r\n" + 
		"\\begin{document}\r\n" + 
		"\\makeexamcover\r\n" + 
		"\r\n" + 
		"";
String coverPageExamDraft="\\documentclass[a4paper,11pt]{article}\r\n" + 
		"\\usepackage{xcolor}\r\n" + 
		"\\usepackage{wits_code}\r\n" + 
		"\\usepackage{wits_exam}\r\n" + 
		"\\usepackage{float}\r\n" + 
		"\\usepackage{graphicx}\r\n" + 
		"\\usepackage{array}\r\n" + 
		"%\\usepackage{wits_flowchart}\r\n" + 
		"%\\usepackage{wits_pseudocode}\r\n" + 
		"\\usepackage{amsmath}\r\n" + 
		"\\usepackage{charter}\r\n" + 
		"\\usepackage{algpseudocode}\r\n" + 
		"\r\n" + 
		"\\usepackage[]{color}\r\n" + 
		"\\usepackage{float}\r\n" + 
		"\\usepackage{subcaption}\r\n" + 
		"\\usepackage{varioref}\r\n" + 
		"\\usepackage{hyperref}\r\n" + 
		"\\usepackage{cleveref}\r\n" + 
		"\r\n" + 
		"\\definecolor{darkgreen}{rgb}{0.0,0.7,0.0}\r\n" + 
		"\\hypersetup{\r\n" + 
		"  colorlinks   = true,              %Colours links instead of ugly boxes\r\n" + 
		"  urlcolor     = blue,              %Colour for external hyperlinks\r\n" + 
		"  linkcolor    = blue,              %Colour of internal links\r\n" + 
		"  citecolor    = darkgreen                %Colour of citations\r\n" + 
		"}\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\r\n" + 
		"\\newcommand{\\todo}{\\textbf{TODO}}\r\n" + 
		"\\titleHeadTime{2}\r\n" + 
		"\\titleHeadDay{4}\r\n" + 
		"\\titleHeadMonth{June}\r\n" + 
		"\\titleHeadYear{2018}\r\n" + 
		"\\titleHeadVenue{Old Mutual Sports Hall}\r\n" + 
		"% First set up fieled that the exams office wants\r\n" + 
		"\\courseno"+"{"+HomePage.CurrentCourse+"}"+"\r\n" + 
		"\\papertitle"+"{"+HomePage.CurrentCourseName+"}"+"\r\n" + 
		"\\testmonth{June}\r\n" + 
		"\\testyear{2018}\r\n" + 
		"%\\yearofstudy{1} % None for Arts and Science\r\n" + 
		"\\degrees{BSc, BSc (Applied Computing), BEconSci}\r\n" + 
		"\\faculties{Science}\r\n" + 
		"\\internalexaminer{Mr Steven James\\\\x-76157}\r\n" + 
		"\\externalexaminer{Prof. Stefan Gruner (UP)}\r\n" + 
		"\\specialmaterial{None}\r\n" + 
		"\\hoursallowance{2 Hours}\r\n" + 
		"\\instructions{\\thetotalmarks\\ Marks available. \\thetotalmarks\\ marks = 100\\%.\\\\ Answer all questions. This is a closed book exam. This exam consists of 12 pages, including this cover page.}\r\n" + 
		"%% Following field not needed for \\examcover but for \\simpleexamhead\r\n" + 
		"%\\department{Computer Science}\r\n" + 
		"%\\solutiontitle{Mark scheme}\r\n" + 
		"\r\n" + 
		"%\\newcommand{\\ansline}{\\\\[15pt]\\rule{\\linewidth}{0.1pt}}\r\n" + 
		"%\\newcommand{\\anslineL}{\\rule{\\linewidth}{0.1pt}}\r\n" + 
		"    \r\n" + 
		"\\usepackage{titling}\r\n" + 
		"\\setlength{\\droptitle}{-7em}   % This is your set screw\r\n" + 
		"\r\n" + 
		"\\usepackage{draftwatermark}\r\n" + 
		"\\SetWatermarkText{Draft}\r\n" + 
		"\\SetWatermarkScale{2}\r\n"+
		"\\begin{document}\r\n" + 
		"\\makeexamcover\r\n" + 
		"\r\n" + 
		"";
}
