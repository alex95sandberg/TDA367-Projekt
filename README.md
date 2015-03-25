# TDA367-Projekt

##Workflow Steps

Assume we have a remote repo and have cloned it locally. We now start working with the local clone.<br>

  1. git checkout master     // You are probably already on local master<br>
  2. git pull                         // (= git pull origin master) To update local master<br>
  3. git checkout -b myTask    // Create and switch to branch myTask<br>
  4. Optional: Use git branch -a to show all branches and git status to see what branch you’re on.<br>
  5. Do some coding (some well defined (sub) task), after max 30 min. … go to 6.<br>
  6. git commit -a -m “blablabla”   // Commit on branch myTask (use better message)<br>
  7. Goto 5. until task finished (max 2 hours before next point)<br>
  8. Ok, assume task finished. Project should be executable, all test should pass.  <br>
  9. Now we start to integrate our changes into the project (Tip: zip current code)<br>
      a. git checkout master<br>
      b. git pull                            // Update local master again<br>
      c. git checkout myTask      // Back to our private branch<br>
      d. git rebase master           // Integrate myTask on top of master<br>
      e. git checkout master<br>
      f. git merge myTask         // Fast forward<br>
      g. git push    // (= git push origin/master) Push to remote repo<br>
      h. git branch -d myTask  // Delete   branch<br>
  10. Now everybody should be able to see our contribution (i.e. git pull).<br>
  11. Continue with next task, go to 2.<br>
