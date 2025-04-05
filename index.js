import jsonfile from "jsonfile";
import moment from "moment";
import simpleGit from "simple-git";

const path = "./data.json";
const git = simpleGit();

function getRandomDateIn2022() {
  const start = moment("2024-06-25");
  const end = moment("2024-10-10");
  const randomDate = moment(start.valueOf() + Math.random() * (end.valueOf() - start.valueOf()));
  randomDate.hour(Math.floor(Math.random() * 24));
  randomDate.minute(Math.floor(Math.random() * 60));
  randomDate.second(Math.floor(Math.random() * 60));
  return randomDate;
}

async function makeCommitWithDate(dateStr, index) {
  const data = { date: dateStr };
  await jsonfile.writeFile(path, data);
  await git.add([path]);
  await git.commit(`Commit ${index + 1} - ${dateStr}`, { '--date': dateStr });
}

async function run() {
  for (let i = 0; i < 65; i++) {
    const randomDate = getRandomDateIn2022().format(); // ISO format
    console.log(`Creating commit ${i + 1} on ${randomDate}`);
    await makeCommitWithDate(randomDate, i);
  }

  await git.push();
  console.log("All commits pushed successfully.");
}

run().catch(console.error);
